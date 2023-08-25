package me.hennerich.contacts.data

import database.ContactEntity
import me.hennerich.contacts.domain.Contact

fun ContactEntity.toContact(): Contact{
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = null // TODO: GET the image
    )
}