package com.example.restrata.terminal.nfc.store

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restrata.terminal.nfc.data.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM card")
    fun getAll(): Flow<List<Card>>

    @Query("SELECT COUNT(1) FROM card WHERE id = :id")
    suspend fun exists(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(card: Card)
}