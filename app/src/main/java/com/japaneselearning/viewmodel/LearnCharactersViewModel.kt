package com.japaneselearning.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.japaneselearning.data.model.*
import com.japaneselearning.data.repository.JapaneseLearningRepository
import kotlinx.coroutines.launch

class LearnCharactersViewModel(
    private val repository: JapaneseLearningRepository
) : ViewModel() {
    
    private val _hiraganaItems = mutableStateListOf<HiraganaCharacter>()
    val hiraganaItems: List<HiraganaCharacter> = _hiraganaItems

    private val _katakanaItems = mutableStateListOf<KatakanaCharacter>()
    val katakanaItems: List<KatakanaCharacter> = _katakanaItems

    private val _kanjiItems = mutableStateListOf<KanjiCharacter>()
    val kanjiItems: List<KanjiCharacter> = _kanjiItems

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _hiraganaItems.addAll(repository.getAllHiragana())
            _katakanaItems.addAll(repository.getAllKatakana())
            _kanjiItems.addAll(repository.getAllKanji())
        }
    }

    fun updateReviewProgress(character: HiraganaCharacter, isCorrect: Boolean) {
        viewModelScope.launch {
            val reviewItem = ReviewItem(
                id = "hiragana_${character.id}",
                contentType = "hiragana",
                contentId = character.id,
                easeFactor = character.easeFactor,
                repetitionCount = character.repetitionCount,
                nextReviewDate = System.currentTimeMillis() + (character.repetitionCount * 24 * 60 * 60 * 1000L),
                isMastered = isCorrect && character.repetitionCount > 5
            )
            repository.addReviewItem(reviewItem)
            repository.updateHiragana(character.copy(isReviewed = true))
        }
    }

    fun updateReviewProgress(character: KatakanaCharacter, isCorrect: Boolean) {
        viewModelScope.launch {
            val reviewItem = ReviewItem(
                id = "katakana_${character.id}",
                contentType = "katakana",
                contentId = character.id,
                easeFactor = character.easeFactor,
                repetitionCount = character.repetitionCount,
                nextReviewDate = System.currentTimeMillis() + (character.repetitionCount * 24 * 60 * 60 * 1000L),
                isMastered = isCorrect && character.repetitionCount > 5
            )
            repository.addReviewItem(reviewItem)
            repository.updateKatakana(character.copy(isReviewed = true))
        }
    }

    fun updateReviewProgress(character: KanjiCharacter, isCorrect: Boolean) {
        viewModelScope.launch {
            val reviewItem = ReviewItem(
                id = "kanji_${character.id}",
                contentType = "kanji",
                contentId = character.id,
                easeFactor = character.easeFactor,
                repetitionCount = character.repetitionCount,
                nextReviewDate = System.currentTimeMillis() + (character.repetitionCount * 24 * 60 * 60 * 1000L),
                isMastered = isCorrect && character.repetitionCount > 10
            )
            repository.addReviewItem(reviewItem)
            repository.updateKanji(character.copy(isReviewed = true))
        }
    }

    companion object {
        fun create(repository: JapaneseLearningRepository): Factory = Factory(repository)
    }

    class Factory(private val repository: JapaneseLearningRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LearnCharactersViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LearnCharactersViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}