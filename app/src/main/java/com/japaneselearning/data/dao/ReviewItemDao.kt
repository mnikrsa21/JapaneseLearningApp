package com.japaneselearning.data.dao

import androidx.room.*

@Dao
interface ReviewItemDao {
    @Query("SELECT * FROM review_item ORDER BY nextReviewDate ASC")
    suspend fun getAllDue(): List<com.japaneselearning.data.model.ReviewItem>

    @Query("SELECT * FROM review_item WHERE contentType = :type ORDER BY nextReviewDate ASC")
    suspend fun getDueByType(type: String): List<com.japaneselearning.data.model.ReviewItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: com.japaneselearning.data.model.ReviewItem)

    @Update
    suspend fun update(item: com.japaneselearning.data.model.ReviewItem)

    @Query("DELETE FROM review_item")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM review_item WHERE nextReviewDate <= :timestamp")
    suspend fun getDueCount(timestamp: Long): Int
}