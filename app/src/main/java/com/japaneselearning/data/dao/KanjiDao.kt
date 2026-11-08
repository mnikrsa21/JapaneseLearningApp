package com.japaneselearning.data.dao

import androidx.room.*

@Dao
interface KanjiDao {
    @Query("SELECT * FROM kanji ORDER BY id")
    suspend fun getAll(): List<com.japaneselearning.data.model.KanjiCharacter>

    @Query("SELECT * FROM kanji WHERE id = :id")
    suspend fun getById(id: Int): com.japaneselearning.data.model.KanjiCharacter?

    @Query("SELECT * FROM kanji WHERE jlptLevel = :level ORDER BY id")
    suspend fun getByLevel(level: Int): List<com.japaneselearning.data.model.KanjiCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<com.japaneselearning.data.model.KanjiCharacter>)

    @Update
    suspend fun update(character: com.japaneselearning.data.model.KanjiCharacter)

    @Query("DELETE FROM kanji")
    suspend fun clearAll()
}