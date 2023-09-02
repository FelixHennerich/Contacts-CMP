package me.hennerich.contacts.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock
import me.hennerich.contacts.domain.Contact
import me.hennerich.contacts.domain.ContactDataSource
import me.hennerich.core.data.ImageStorage
import me.hennerich.database.ContactsDatabase


class SqlDelightContactDataSource(
    db: ContactsDatabase,
    private val imageStorage: ImageStorage
): ContactDataSource, ViewModel() {

    private val queries = db.contactQueries
    private val myScope = viewModelScope
    override fun getContact(): Flow<List<Contact>> {
        return queries
            .getContacts()
            .asFlow().mapToList(myScope.coroutineContext)
            .map {  contactEntities ->
                supervisorScope { contactEntities
                    .map {
                        async { it.toContact(imageStorage) }
                    }
                    .map { it.await() }
                }
            }
    }


    override fun getRecentContacts(amount: Int): Flow<List<Contact>> {
        return queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList(myScope.coroutineContext)
            .map { contactEntities ->
                supervisorScope { contactEntities
                    .map {
                        async { it.toContact(imageStorage) }
                    }
                    .map { it.await() }
                }
            }
    }

    override suspend fun insertContact(contact: Contact) {
        val imagePath = contact.photoBytes?.let {
            imageStorage.saveImage(it)
        }
        queries.insertContactEntity(
            id = contact.id,
            firstName =  contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
            email = contact.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = imagePath
        )
    }

    override suspend fun deleteContact(id: Long) {
        val entity = queries.getContactById(id).executeAsOne()
        entity.imagePath?.let{
            imageStorage.deleteImage(it)
        }
        queries.deleteContact(id)
    }


}