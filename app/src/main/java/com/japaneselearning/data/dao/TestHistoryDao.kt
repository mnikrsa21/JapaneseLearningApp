package com.japaneselearning.data.dao

import androidx.room.*

@Dao
interface TestHistoryDao {
    @Query("SELECT * FROM test_history ORDER BY dateTaken DESC")
    suspend fun getAll(): List<com.japaneselearning.data.model.TestHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: com.japaneselearning.data.model.TestHistory)

    @Query("SELECT * FROM test_history WHERE category = :category ORDER BY dateTaken DESC")
    suspend fun getByCategory(category: String): List<com.japaneselearning.data.model.TestHistory>

    @Query("DELETE FROM test_history")
    suspend fun clearAll()
}