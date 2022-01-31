package com.example.restrata.terminal.nfc.activity.cardlist

import androidx.lifecycle.ViewModel
import com.example.restrata.terminal.nfc.manager.CardManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val cardManager: CardManager
) : ViewModel() {

    val cardList get() = cardManager.cards
}