package com.japaneselearning.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.japaneselearning.data.model.*
import com.japaneselearning.data.repository.JapaneseLearningRepository
import kotlinx.coroutines.launch

class WritingViewModel(
    private val repository: JapaneseLearningRepository
) : ViewModel() {
    
    private val _currentStrokes = mutableStateListOf<String>()
    val currentStrokes: List<String> = _currentStrokes

    private val _userStrokes = mutableStateListOf<String>()
    val userStrokes: List<String> = _userStrokes

    private var currentCharacter: HiraganaCharacter? = null
    private var currentKatakana: KatakanaCharacter? = null
    private var currentKanji: KanjiCharacter? = null

    fun loadCharacter(type: String, id: Int) {
        viewModelScope.launch {
            when (type) {
                "hiragana" -> {
                    currentCharacter = repository.getHiraganaById(id)
                    currentCharacter?.let { 
                        _currentStrokes.clear()
                        _currentStrokes.addAll(it.strokeOrder.split(","))
                    }
                }
                "katakana" -> {
                    currentKatakana = repository.getKatakanaById(id)
                    currentKatakana?.let { 
                        _currentStrokes.clear()
                        _currentStrokes.addAll(it.strokeOrder.split(","))
                    }
                }
                "kanji" -> {
                    currentKanji = repository.getKanjiById(id)
                    currentKanji?.let { 
                        _currentStrokes.clear()
                        _currentStrokes.addAll(listOf("stroke_1", "stroke_2", "stroke_3"))
                    }
                }
            }
        }
    }

    fun addStroke(stroke: String) {
        _userStrokes.add(stroke)
    }

    fun clearStrokes() {
        _userStrokes.clear()
    }

    fun getCurrentCharacterInfo(): CharacterInfo? {
        return when {
            currentCharacter != null -> CharacterInfo(
                type = "hiragana",
                character = currentCharacter?.character ?: "",
                reading = currentCharacter?.romaji ?: "",
                strokes = currentCharacter?.strokeOrder?.split(",") ?: emptyList()
            )
            currentKatakana != null -> CharacterInfo(
                type = "katakana",
                character = currentKatakana?.character ?: "",
                reading = currentKatakana?.romaji ?: "",
                strokes = currentKatakana?.strokeOrder?.split(",") ?: emptyList()
            )
            currentKanji != null -> CharacterInfo(
                type = "kanji",
                character = currentKanji?.character ?: "",
                reading = currentKanji?.kunyomi?.firstOrNull() ?: "",
                strokes = currentKanji?.strokeCount?.times("stroke_") ?: "stroke_1"
            )
            else -> null
        }
    }

    fun checkAnswer(userInput: String): Boolean {
        val correct = when {
            currentCharacter != null -> userInput == currentCharacter?.hiragana ?: ""
            currentKatakana != null -> userInput == currentKatakana?.katakana ?: ""
            currentKanji != null -> userInput == currentKanji?.kanji ?: ""
            else -> false
        }
        
        if (correct) {
            updateProgress()
        }
        return correct
    }

    private fun updateProgress() {
        viewModelScope.launch {
            val item = when {
                currentCharacter != null -> ReviewItem(
                    id = "hiragana_${currentCharacter?.id}",
                    contentType = "hiragana",
                    contentId = currentCharacter?.id ?: 0,
                    easeFactor = 2.5f,
                    repetitionCount = 0,
                    nextReviewDate = System.currentTimeMillis() + 86400000L
                )
                currentKatakana != null -> ReviewItem(
                    id = "katakana_${currentKatakana?.id}",
                    contentType = "katakana",
                    contentId = currentKatakana?.id ?: 0,
                    easeFactor = 2.5f,
                    repetitionCount = 0,
                    nextReviewDate = System.currentTimeMillis() + 86400000L
                )
                currentKanji != null -> ReviewItem(
                    id = "kanji_${currentKanji?.id}",
                    contentType = "kanji",
                    contentId = currentKanji?.id ?: 0,
                    easeFactor = 2.5f,
                    repetitionCount = 0,
                    nextReviewDate = System.currentTimeMillis() + 86400000L
                )
                else -> return@launch
            }
            repository.addReviewItem(item)
        }
    }

    companion object {
        fun create(repository: JapaneseLearningRepository): Factory = Factory(repository)
    }

    class Factory(private val repository: JapaneseLearningRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WritingViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WritingViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

data class CharacterInfo(
    val type: String,
    val character: String,
    val reading: String,
    val strokes: List<String>
)

private fun HiraganaCharacter.hiragana(): String = this.character
private fun KatakanaCharacter.katakana(): String = this.character
private fun KanjiCharacter.kanji(): String = this.character