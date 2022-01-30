package com.example.restrata.terminal.nfc.di

import android.content.Context
import androidx.room.Room
import com.example.restrata.terminal.nfc.store.NFCDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Provides
    fun database(@ApplicationContext appContext: Context) =
        Room
            .databaseBuilder(
                appContext,
                NFCDatabase::class.java,
                "nfc"
            )
            .build()
}