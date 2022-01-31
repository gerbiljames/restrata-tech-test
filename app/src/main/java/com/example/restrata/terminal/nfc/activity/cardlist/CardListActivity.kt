package com.example.restrata.terminal.nfc.activity.cardlist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.restrata.terminal.nfc.R
import com.example.restrata.terminal.nfc.data.Card
import com.example.restrata.terminal.nfc.ui.theme.NFCTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@AndroidEntryPoint
class CardListActivity : AppCompatActivity() {

    private val viewModel: CardListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setContent {
            NFCTheme {
                CardListContent(viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun CardListContent(viewModel: CardListViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        CardList(viewModel = viewModel)
    }
}

@Composable
private fun CardList(viewModel: CardListViewModel) {
    val cardList by viewModel.cardList.collectAsState(initial = emptyList())
    val dateFormat = remember { DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT) }

    LazyColumn {
        items(
            items = cardList,
            itemContent = { CardListItem(card = it, dateFormat = dateFormat) }
        )
    }
}

@Composable
private fun CardListItem(card: Card, dateFormat: DateTimeFormatter) {
    Row {
        Column {
            Text(text = stringResource(id = R.string.card_id, card.id), style = typography.h6)
            Text(text = card.timestamp.format(dateFormat), style = typography.caption)
        }
    }
}