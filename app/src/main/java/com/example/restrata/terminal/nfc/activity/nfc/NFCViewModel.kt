package com.example.restrata.terminal.nfc.activity.nfc

import android.nfc.Tag
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restrata.terminal.nfc.data.Card
import com.example.restrata.terminal.nfc.manager.CardManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NFCViewModel @Inject constructor(
    private val cardManager: CardManager
) : ViewModel() {

    var card by mutableStateOf<Pair<Card, Boolean>?>(null)
        private set

    fun onNewTag(tag: Tag) {
        Card(id = tag.id.toHex(), timestamp = LocalDateTime.now()).let {
            viewModelScope.launch {
                card = it to cardManager.cardExists(it)
                cardManager.addCard(it)
            }
        }
    }
    
    private fun ByteArray.toHex(): String =
        asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }
}