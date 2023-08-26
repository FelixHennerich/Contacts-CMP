package me.hennerich.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import me.hennerich.database.ContactsDatabase

actual class DatabaseDriverFactory (
    private val context: Context
){
    actual fun create(): SqlDriver{
        return AndroidSqliteDriver(
            ContactsDatabase.Schema,
            context,
            "contact.db"
        )
    }
}