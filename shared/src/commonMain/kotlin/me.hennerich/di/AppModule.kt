package me.hennerich.di

import me.hennerich.contacts.domain.ContactDataSource

expect class AppModule {
    val contactDataSource: ContactDataSource
}