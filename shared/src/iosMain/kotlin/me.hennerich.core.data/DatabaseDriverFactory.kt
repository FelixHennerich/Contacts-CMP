package me.hennerich.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import me.hennerich.database.ContactsDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver{
        return NativeSqliteDriver(ContactsDatabase.Schema, "contact.db")
    }
}