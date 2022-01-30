package com.example.restrata.terminal.nfc.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Card(
    @PrimaryKey val id: String,
    val timestamp: LocalDateTime
)