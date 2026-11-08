package com.japaneselearning.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.japaneselearning.data.dao.*
import com.japaneselearning.data.model.*

@Database(
    entities = [
        HiraganaCharacter::class,
        KatakanaCharacter::class,
        KanjiCharacter::class,
        Vocabulary::class,
        Verb::class,
        TestHistory::class,
        UserProgress::class,
        ReviewItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hiraganaDao(): HiraganaDao
    abstract fun katakanaDao(): KatakanaDao
    abstract fun kanjiDao(): KanjiDao
    abstract fun vocabularyDao(): VocabularyDao
    abstract fun verbDao(): VerbDao
    abstract fun testHistoryDao(): TestHistoryDao
    abstract fun userProgressDao(): UserProgressDao
    abstract fun reviewItemDao(): ReviewItemDao
}