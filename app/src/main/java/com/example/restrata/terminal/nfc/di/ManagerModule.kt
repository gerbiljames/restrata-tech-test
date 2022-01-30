package com.example.restrata.terminal.nfc.di

import com.example.restrata.terminal.nfc.manager.CardManager
import com.example.restrata.terminal.nfc.manager.CardManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {

    @Binds
    fun cardManager(manager: CardManagerImpl): CardManager
}