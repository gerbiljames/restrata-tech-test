package com.example.restrata.terminal.nfc.manager

import com.example.restrata.terminal.nfc.data.Card
import com.example.restrata.terminal.nfc.store.NFCDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardManagerImpl @Inject constructor(
    private val database: NFCDatabase
): CardManager {

    override val cards: Flow<List<Card>>
        get() = database.userDao().getAll()

    override suspend fun addCard(card: Card) =
        database.userDao().insert(card)

    override suspend fun cardExists(card: Card): Boolean =
        database.userDao().exists(card.id)
}