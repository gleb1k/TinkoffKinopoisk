package ru.glebik.core.presentation.di

import android.util.Log
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.logger.Logger
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.koin.dsl.module

val presentationModule = module {

    factory<StoreFactory> {
        val logger = object : Logger {
            override fun log(text: String) {
                Log.v("StoreFactoryLogger", text)
            }
        }
        LoggingStoreFactory(DefaultStoreFactory(), logger = logger)
    }
}