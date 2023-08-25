package me.hennerich.contacts.domain

import kotlinx.coroutines.flow.Flow

interface ContactDataSource {

    fun getContact(): Flow<List<Contact>>
    fun getRecentContacts(amount: Int): Flow<List<Contact>>
    suspend fun insertContact(contact: Contact)
    suspend fun deleteContact(id: Long)
}