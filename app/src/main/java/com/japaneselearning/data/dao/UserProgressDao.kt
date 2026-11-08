package com.japaneselearning.data.dao

import androidx.room.*

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress WHERE id = 1")
    suspend fun get(): com.japaneselearning.data.model.UserProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(progress: com.japaneselearning.data.model.UserProgress): Long

    @Update
    suspend fun update(progress: com.japaneselearning.data.model.UserProgress)

    @Query("DELETE FROM user_progress")
    suspend fun clearAll()
}