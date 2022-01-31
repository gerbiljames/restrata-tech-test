package com.example.restrata.terminal.nfc.activity.nfc

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.restrata.terminal.nfc.R
import com.example.restrata.terminal.nfc.ui.theme.NFCTheme
import com.example.restrata.terminal.nfc.ui.theme.Teal200
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NFCActivity : ComponentActivity() {

    private val viewModel: NFCViewModel by viewModels()

    @Inject
    lateinit var coordinator: NFCCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readFromIntent(intent)
        setupView()
    }

    private fun setupView() {
        setContent {
            NFCTheme {
                NFCContent(viewModel = viewModel, coordinator = coordinator)
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
private fun NFCContent(viewModel: NFCViewModel, coordinator: NFCCoordinator) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(viewModel = viewModel)
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CardListButton(coordinator = coordinator)
            }
        }
    }
}

@Composable
private fun Card(viewModel: NFCViewModel) {
    Text(
        text = viewModel.card?.let {
            stringResource(id = R.string.card_id, it.first.id)
        } ?: stringResource(id = R.string.nfc_waiting),

        color = when (viewModel.card?.second) {
            true -> Teal200
            else -> Color.Unspecified
        }
    )
}

@Composable
private fun CardListButton(coordinator: NFCCoordinator) {
    Button(onClick = { coordinator.showCardList() }) {
        Text(text = stringResource(id = R.string.show_card_list))
    }
}
