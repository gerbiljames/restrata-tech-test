package com.example.restrata.terminal.nfc.data

import java.time.LocalDateTime

data class Card(
    val id: String,
    val timestamp: LocalDateTime
)