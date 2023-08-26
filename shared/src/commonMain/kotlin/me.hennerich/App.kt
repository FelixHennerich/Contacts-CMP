package me.hennerich

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import me.hennerich.contacts.presentation.ContactListScreen
import me.hennerich.contacts.presentation.ContactListViewModel
import me.hennerich.core.presentation.ContactsTheme
import me.hennerich.di.AppModule

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule
) {
    ContactsTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
        ){
        val viewModel = getViewModel(
            key = "contact-list-screen",
            factory = viewModelFactory {
                ContactListViewModel(appModule.contactDataSource)
            }
        )
        val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            ContactListScreen(
                state = state,
                newContact = viewModel.newContact,
                onEvent = viewModel::onEvent
            )
        }
    }
}