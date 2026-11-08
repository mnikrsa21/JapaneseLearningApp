package com.japaneselearning.data.dao

import androidx.room.*

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary ORDER BY id")
    suspend fun getAll(): List<com.japaneselearning.data.model.Vocabulary>

    @Query("SELECT * FROM vocabulary WHERE category = :category ORDER BY id")
    suspend fun getByCategory(category: String): List<com.japaneselearning.data.model.Vocabulary>

    @Query("SELECT * FROM vocabulary WHERE id = :id")
    suspend fun getById(id: Int): com.japaneselearning.data.model.Vocabulary?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vocabularies: List<com.japaneselearning.data.model.Vocabulary>)

    @Update
    suspend fun update(vocabulary: com.japaneselearning.data.model.Vocabulary)

    @Query("DELETE FROM vocabulary")
    suspend fun clearAll()
}