package com.japaneselearning.data.dao

import androidx.room.*

@Dao
interface HiraganaDao {
    @Query("SELECT * FROM hiragana ORDER BY id")
    suspend fun getAll(): List<com.japaneselearning.data.model.HiraganaCharacter>

    @Query("SELECT * FROM hiragana WHERE id = :id")
    suspend fun getById(id: Int): com.japaneselearning.data.model.HiraganaCharacter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<com.japaneselearning.data.model.HiraganaCharacter>)

    @Update
    suspend fun update(character: com.japaneselearning.data.model.HiraganaCharacter)

    @Query("DELETE FROM hiragana")
    suspend fun clearAll()
}