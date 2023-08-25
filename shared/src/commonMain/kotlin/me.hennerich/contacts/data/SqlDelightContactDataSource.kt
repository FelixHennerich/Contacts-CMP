package me.hennerich.contacts.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import me.hennerich.contacts.domain.Contact
import me.hennerich.contacts.domain.ContactDataSource
import me.hennerich.database.ContactsDatabase


class SqlDelightContactDataSource(
    db: ContactsDatabase
): ContactDataSource, ViewModel() {

    private val queries = db.contactQueries
    private val myScope = viewModelScope
    override fun getContact(): Flow<List<Contact>> {
        return queries
            .getContacts()
            .asFlow().mapToList(myScope.coroutineContext)
            .map {
                it.map {
                    it.toContact()
                }
            }
    }


    override fun getRecentContacts(amount: Int): Flow<List<Contact>> {
        return queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList(myScope.coroutineContext)
            .map { contactEntities ->
                contactEntities.map { contactEntity ->
                    contactEntity.toContact()
                }
            }
    }

    override suspend fun insertContact(contact: Contact) {
        queries.insertContactEntity(
            id = contact.id,
            firstName =  contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
            email = contact.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = null
        )
    }

    override suspend fun deleteContact(id: Long) {
        queries.deleteContact(id)
    }


}