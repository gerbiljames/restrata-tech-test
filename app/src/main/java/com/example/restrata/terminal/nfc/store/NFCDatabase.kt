package com.example.restrata.terminal.nfc.store

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restrata.terminal.nfc.data.Card

@Database(entities = [Card::class], version = 1)
@TypeConverters(Converters::class)
abstract class NFCDatabase : RoomDatabase() {
    abstract fun userDao(): CardDao
}
