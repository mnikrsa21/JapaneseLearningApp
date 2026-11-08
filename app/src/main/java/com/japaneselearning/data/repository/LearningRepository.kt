package com.japaneselearning.data.repository

import com.japaneselearning.data.dao.*
import com.japaneselearning.data.model.*
import com.japaneselearning.data.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JapaneseLearningRepository private constructor(
    private val hiraganaDao: HiraganaDao,
    private val katakanaDao: KatakanaDao,
    private val kanjiDao: KanjiDao,
    private val vocabularyDao: VocabularyDao,
    private val verbDao: VerbDao,
    private val testHistoryDao: TestHistoryDao,
    private val userProgressDao: UserProgressDao,
    private val reviewItemDao: ReviewItemDao
) {
    companion object {
        @Volatile
        private var INSTANCE: JapaneseLearningRepository? = null

        fun getInstance(database: AppDatabase): JapaneseLearningRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: JapaneseLearningRepository(
                    database.hiraganaDao(),
                    database.katakanaDao(),
                    database.kanjiDao(),
                    database.vocabularyDao(),
                    database.verbDao(),
                    database.testHistoryDao(),
                    database.userProgressDao(),
                    database.reviewItemDao()
                ).also { INSTANCE = it }
            }
        }
    }

    // Hiragana
    suspend fun getAllHiragana(): List<HiraganaCharacter> = hiraganaDao.getAll()
    suspend fun getHiraganaById(id: Int): HiraganaCharacter? = hiraganaDao.getById(id)
    suspend fun updateHiragana(character: HiraganaCharacter) = hiraganaDao.update(character)

    // Katakana
    suspend fun getAllKatakana(): List<KatakanaCharacter> = katakanaDao.getAll()
    suspend fun getKatakanaById(id: Int): KatakanaCharacter? = katakanaDao.getById(id)
    suspend fun updateKatakana(character: KatakanaCharacter) = katakanaDao.update(character)

    // Kanji
    suspend fun getAllKanji(): List<KanjiCharacter> = kanjiDao.getAll()
    suspend fun getKanjiById(id: Int): KanjiCharacter? = kanjiDao.getById(id)
    suspend fun getKanjiByLevel(level: Int): List<KanjiCharacter> = kanjiDao.getByLevel(level)
    suspend fun updateKanji(character: KanjiCharacter) = kanjiDao.update(character)

    // Vocabulary
    suspend fun getAllVocabulary(): List<Vocabulary> = vocabularyDao.getAll()
    suspend fun getVocabularyByCategory(category: String): List<Vocabulary> = vocabularyDao.getByCategory(category)
    suspend fun updateVocabulary(vocabulary: Vocabulary) = vocabularyDao.update(vocabulary)

    // Verbs
    suspend fun getAllVerbs(): List<Verb> = verbDao.getAll()
    suspend fun getVerbsByType(type: String): List<Verb> = verbDao.getByType(type)
    suspend fun updateVerb(verb: Verb) = verbDao.update(verb)

    // Test History
    suspend fun getTestHistory(): List<TestHistory> = testHistoryDao.getAll()
    suspend fun addTestHistory(history: TestHistory) = testHistoryDao.insert(history)
    suspend fun getTestHistoryByCategory(category: String): List<TestHistory> = testHistoryDao.getByCategory(category)

    // User Progress
    suspend fun getUserProgress(): UserProgress? = userProgressDao.get()
    suspend fun updateUserProgress(progress: UserProgress) = userProgressDao.update(progress)
    suspend fun initializeUserProgress() = userProgressDao.insert(UserProgress())

    // Review Items (Spaced Repetition)
    suspend fun getDueReviewItems(): List<ReviewItem> = reviewItemDao.getAllDue()
    suspend fun getDueReviewItemsByType(type: String): List<ReviewItem> = reviewItemDao.getDueByType(type)
    suspend fun addReviewItem(item: ReviewItem) = reviewItemDao.insert(item)
    suspend fun updateReviewItem(item: ReviewItem) = reviewItemDao.update(item)
    suspend fun getReviewItemCount(): Int {
        val progress = userProgressDao.get()
        return progress?.let { reviewItemDao.getDueCount(it.lastStudyDate ?: System.currentTimeMillis()) } ?: 0
    }
}