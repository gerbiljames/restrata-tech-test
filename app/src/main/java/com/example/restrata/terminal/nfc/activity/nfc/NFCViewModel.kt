package com.example.restrata.terminal.nfc.activity.nfc

import android.nfc.Tag
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NFCViewModel @Inject constructor() : ViewModel() {

    var card by mutableStateOf<String?>(null)
        private set

    fun onNewTag(tag: Tag) {
        card = "tag.id=${tag.id.toHex2()}"
    }

    @ExperimentalUnsignedTypes
    private fun ByteArray.toHex2(): String =
        asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }
}