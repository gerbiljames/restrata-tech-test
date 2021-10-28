package com.example.restrata.terminal.nfc

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.restrata.terminal.nfc.ui.theme.NFCTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.UnsupportedEncodingException

class MainActivity : ComponentActivity() {
    private val _card: MutableStateFlow<String> = MutableStateFlow("")
    private val card: StateFlow<String> = _card
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val newCardInfo: String = card.collectAsState().value

            NFCTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(newCardInfo)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        readFromIntent(intent)
    }

    private fun readFromIntent(intent: Intent) {
        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        _card.value = "tag.id=${tag?.id?.toHex2()}"
    }
}

@ExperimentalUnsignedTypes
fun ByteArray.toHex2(): String =
    asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }

@Composable
fun Greeting(name: String) {
    Text(text = name)
}
