package com.japaneselearning.data.dao

import androidx.room.*

@Dao
interface KatakanaDao {
    @Query("SELECT * FROM katakana ORDER BY id")
    suspend fun getAll(): List<com.japaneselearning.data.model.KatakanaCharacter>

    @Query("SELECT * FROM katakana WHERE id = :id")
    suspend fun getById(id: Int): com.japaneselearning.data.model.KatakanaCharacter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<com.japaneselearning.data.model.KatakanaCharacter>)

    @Update
    suspend fun update(character: com.japaneselearning.data.model.KatakanaCharacter)

    @Query("DELETE FROM katakana")
    suspend fun clearAll()
}