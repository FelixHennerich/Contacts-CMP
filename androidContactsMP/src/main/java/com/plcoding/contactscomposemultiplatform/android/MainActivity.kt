package com.plcoding.contactscomposemultiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.LocalContext
import me.hennerich.App
import me.hennerich.core.presentation.ImagePickerFactory
import me.hennerich.di.AppModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true,
                appModule = AppModule(LocalContext.current.applicationContext),
                imagePicker = ImagePickerFactory().createPicker()
            )
        }
    }
}