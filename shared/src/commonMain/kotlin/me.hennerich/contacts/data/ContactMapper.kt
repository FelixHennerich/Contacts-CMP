package me.hennerich.contacts.data

import database.ContactEntity
import me.hennerich.contacts.domain.Contact
import me.hennerich.core.data.ImageStorage

suspend fun ContactEntity.toContact(imageStorage: ImageStorage): Contact{
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = imagePath?.let { imageStorage.getImage(it) }
    )
}