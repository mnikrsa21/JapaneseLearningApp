package com.japaneselearning.data.dao

import androidx.room.*

@Dao
interface VerbDao {
    @Query("SELECT * FROM verbs ORDER BY id")
    suspend fun getAll(): List<com.japaneselearning.data.model.Verb>

    @Query("SELECT * FROM verbs WHERE type = :type ORDER BY id")
    suspend fun getByType(type: String): List<com.japaneselearning.data.model.Verb>

    @Query("SELECT * FROM verbs WHERE id = :id")
    suspend fun getById(id: Int): com.japaneselearning.data.model.Verb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(verbs: List<com.japaneselearning.data.model.Verb>)

    @Update
    suspend fun update(verb: com.japaneselearning.data.model.Verb)

    @Query("DELETE FROM verbs")
    suspend fun clearAll()
}