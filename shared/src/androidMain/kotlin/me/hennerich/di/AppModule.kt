package me.hennerich.di

import android.content.Context
import me.hennerich.contacts.data.SqlDelightContactDataSource
import me.hennerich.contacts.domain.ContactDataSource
import me.hennerich.core.data.DatabaseDriverFactory
import me.hennerich.database.ContactsDatabase

actual class AppModule(
    private val context: Context
) {

    actual val contactDataSource: ContactDataSource by lazy{
        SqlDelightContactDataSource(
            db = ContactsDatabase(
                driver = DatabaseDriverFactory(context).create()
            )
        )
    }
}