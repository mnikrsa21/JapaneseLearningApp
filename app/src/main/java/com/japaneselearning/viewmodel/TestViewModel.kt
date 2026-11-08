package com.japaneselearning.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.japaneselearning.data.model.*
import com.japaneselearning.data.repository.JapaneseLearningRepository
import kotlinx.coroutines.launch

class TestViewModel(
    private val repository: JapaneseLearningRepository
) : ViewModel() {
    
    private val _vocabQuestions = mutableStateListOf<Vocabulary>()
    val vocabQuestions: List<Vocabulary> = _vocabQuestions

    private val _verbQuestions = mutableStateListOf<Verb>()
    val verbQuestions: List<Verb> = _verbQuestions

    private val _currentScore = mutableStateListOf<Int>()
    val currentScore: List<Int> = _currentScore

    fun loadTestQuestions(type: String, category: String? = null) {
        viewModelScope.launch {
            when (type) {
                "vocabulary" -> {
                    val all = repository.getAllVocabulary()
                    _vocabQuestions.clear()
                    _vocabQuestions.addAll(all.shuffled().take(10))
                }
                "verbs" -> {
                    val all = repository.getAllVerbs()
                    _verbQuestions.clear()
                    _verbQuestions.addAll(all.shuffled().take(10))
                }
            }
        }
    }

    fun recordAnswer(isCorrect: Boolean) {
        _currentScore.add(if (isCorrect) 1 else 0)
    }

    fun getFinalScore(): Int {
        return _currentScore.sum()
    }

    fun saveTestHistory(testType: String, category: String, total: Int, correct: Int) {
        viewModelScope.launch {
            repository.addTestHistory(
                TestHistory(
                    id = System.currentTimeMillis(),
                    testType = testType,
                    score = correct,
                    totalQuestions = total,
                    dateTaken = System.currentTimeMillis(),
                    category = category
                )
            )
        }
    }

    companion object {
        fun create(repository: JapaneseLearningRepository): Factory = Factory(repository)
    }

    class Factory(private val repository: JapaneseLearningRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TestViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}