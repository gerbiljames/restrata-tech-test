package com.example.restrata.terminal.nfc.activity.nfc

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.restrata.terminal.nfc.R
import com.example.restrata.terminal.nfc.ui.theme.NFCTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: NFCViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setContent {
            NFCTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Card(viewModel = viewModel)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        readFromIntent(intent)
    }

    private fun readFromIntent(intent: Intent) {
        intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)?.let {
            viewModel.onNewTag(it)
        }
    }
}

@Composable
fun Card(viewModel: NFCViewModel) {
    Text(text =
        viewModel.card?.let {
            stringResource(id = R.string.card_id, it.id)
        } ?: stringResource(id = R.string.nfc_waiting)
    )
}
