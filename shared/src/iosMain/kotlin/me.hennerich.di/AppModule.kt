package me.hennerich.di

import me.hennerich.contacts.data.SqlDelightContactDataSource
import me.hennerich.contacts.domain.ContactDataSource
import me.hennerich.core.data.DatabaseDriverFactory
import me.hennerich.core.data.ImageStorage
import me.hennerich.database.ContactsDatabase

actual class AppModule{

    actual val contactDataSource: ContactDataSource by lazy{
        SqlDelightContactDataSource(
            db = ContactsDatabase(
                driver = DatabaseDriverFactory().create()
            ),
            imageStorage = ImageStorage()
        )
    }
}