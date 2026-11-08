package com.japaneselearning.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hiragana")
data class HiraganaCharacter(
    @PrimaryKey val id: Int,
    val character: String,
    val romaji: String,
    val strokeOrder: String,
    val examples: List<String> = emptyList(),
    val category: String = "basic",
    val isReviewed: Boolean = false,
    val easeFactor: Float = 2.5f,
    val repetitionCount: Int = 0,
    val nextReviewDate: Long? = null
)

@Entity(tableName = "katakana")
data class KatakanaCharacter(
    @PrimaryKey val id: Int,
    val character: String,
    val romaji: String,
    val strokeOrder: String,
    val examples: List<String> = emptyList(),
    val category: String = "basic",
    val isReviewed: Boolean = false,
    val easeFactor: Float = 2.5f,
    val repetitionCount: Int = 0,
    val nextReviewDate: Long? = null
)

@Entity(tableName = "kanji")
data class KanjiCharacter(
    @PrimaryKey val id: Int,
    val character: String,
    val meanings: List<String> = emptyList(),
    val onyomi: List<String> = emptyList(),
    val kunyomi: List<String> = emptyList(),
    val strokeCount: Int,
    val jlptLevel: Int? = null,
    val examples: List<String> = emptyList(),
    val isReviewed: Boolean = false,
    val easeFactor: Float = 2.5f,
    val repetitionCount: Int = 0,
    val nextReviewDate: Long? = null
)

@Entity(tableName = "vocabulary")
data class Vocabulary(
    @PrimaryKey val id: Int,
    val word: String,
    val reading: String,
    val meaning: String,
    val partOfSpeech: String,
    val exampleSentence: String? = null,
    val exampleTranslation: String? = null,
    val category: String = "common",
    val isReviewed: Boolean = false,
    val easeFactor: Float = 2.5f,
    val repetitionCount: Int = 0,
    val nextReviewDate: Long? = null
)

@Entity(tableName = "verbs")
data class Verb(
    @PrimaryKey val id: Int,
    val verb: String,
    val reading: String,
    val meaning: String,
    val type: String,
    val conjugationPatterns: List<String> = emptyList(),
    val examples: List<String> = emptyList(),
    val isReviewed: Boolean = false,
    val easeFactor: Float = 2.5f,
    val repetitionCount: Int = 0,
    val nextReviewDate: Long? = null
)

@Entity(tableName = "test_history")
data class TestHistory(
    @PrimaryKey val id: Long,
    val testType: String,
    val score: Int,
    val totalQuestions: Int,
    val dateTaken: Long,
    val category: String
)

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey val id: Int = 1,
    val totalStudyTime: Long = 0L,
    val charactersLearned: Int = 0,
    val testsTaken: Int = 0,
    val lastStudyDate: Long? = null
)

@Entity(tableName = "review_item")
data class ReviewItem(
    @PrimaryKey val id: String,
    val contentType: String,
    val contentId: Int,
    val easeFactor: Float,
    val repetitionCount: Int,
    val nextReviewDate: Long,
    val isMastered: Boolean = false
)