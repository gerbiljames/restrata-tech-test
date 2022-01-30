package com.example.restrata.terminal.nfc.activity.nfc

import android.nfc.Tag
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.restrata.terminal.nfc.data.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NFCViewModel @Inject constructor() : ViewModel() {

    var card by mutableStateOf<Card?>(null)
        private set

    fun onNewTag(tag: Tag) {
        card = Card(id = tag.id.toHex2(), timestamp = LocalDateTime.now())
    }

    @ExperimentalUnsignedTypes
    private fun ByteArray.toHex2(): String =
        asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }
}