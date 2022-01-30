package com.example.restrata.terminal.nfc.activity.nfc

import android.nfc.Tag
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NFCViewModel @Inject constructor() : ViewModel() {

    val card: LiveData<String?> get() = _card
    private val _card = MutableLiveData<String?>()

    fun onNewTag(tag: Tag) {
        _card.postValue("tag.id=${tag.id.toHex2()}")
    }

    @ExperimentalUnsignedTypes
    private fun ByteArray.toHex2(): String =
        asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }
}