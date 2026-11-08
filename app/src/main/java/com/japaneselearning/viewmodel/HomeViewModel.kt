package com.japaneselearning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.japaneselearning.data.repository.JapaneseLearningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: JapaneseLearningRepository) : ViewModel() {
    private val _totalCharacters = MutableStateFlow(0)
    val totalCharacters: StateFlow<Int> = _totalCharacters

    private val _kanjiCount = MutableStateFlow(0)
    val kanjiCount: StateFlow<Int> = _kanjiCount

    private val _vocabCount = MutableStateFlow(0)
    val vocabCount: StateFlow<Int> = _vocabCount

    private val _verbCount = MutableStateFlow(0)
    val verbCount: StateFlow<Int> = _verbCount

    private val _dueReviews = MutableStateFlow(0)
    val dueReviews: StateFlow<Int> = _dueReviews

    init {
        refreshStats()
    }

    private fun refreshStats() {
        viewModelScope.launch {
            val hiragana = repository.getAllHiragana().size
            val katakana = repository.getAllKatakana().size
            val kanji = repository.getAllKanji().size
            val vocab = repository.getAllVocabulary().size
            val verbs = repository.getAllVerbs().size

            _totalCharacters.value = hiragana + katakana
            _kanjiCount.value = kanji
            _vocabCount.value = vocab
            _verbCount.value = verbs
        }
    }

    fun loadDueReviews() {
        viewModelScope.launch {
            val progress = repository.getUserProgress()
            val count = progress?.let { 
                repository.reviewItemDao.getDueCount(it.lastStudyDate ?: System.currentTimeMillis())
            } ?: 0
            _dueReviews.value = count
        }
    }

    companion object {
        fun create(repository: JapaneseLearningRepository): Factory = Factory(repository)
    }

    class Factory(private val repository: JapaneseLearningRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}