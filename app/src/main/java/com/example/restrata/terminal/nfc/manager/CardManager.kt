package com.example.restrata.terminal.nfc.manager

import com.example.restrata.terminal.nfc.data.Card
import kotlinx.coroutines.flow.Flow

interface CardManager {

    val cards: Flow<List<Card>>

    suspend fun addCard(card: Card)
    suspend fun cardExists(card: Card): Boolean
}