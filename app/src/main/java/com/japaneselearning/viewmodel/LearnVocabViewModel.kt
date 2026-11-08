package com.japaneselearning.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.japaneselearning.data.model.*
import com.japaneselearning.data.repository.JapaneseLearningRepository
import kotlinx.coroutines.launch

class LearnVocabViewModel(
    private val repository: JapaneseLearningRepository
) : ViewModel() {
    
    private val _vocabularyByCategory = mutableStateListOf<Pair<String, List<Vocabulary>>>()
    val vocabularyByCategory: List<Pair<String, List<Vocabulary>>> = _vocabularyByCategory

    private val _selectedCategory = mutableStateListOf<String>()
    val selectedCategory: List<String> = _selectedCategory

    init {
        loadVocabulary()
    }

    private fun loadVocabulary() {
        viewModelScope.launch {
            val allVocab = repository.getAllVocabulary()
            val categories = setOf("common", "food", "travel", "numbers", "family", "work", "school", "weather")
            
            categories.forEach { category ->
                val catVocab = repository.getVocabularyByCategory(category)
                if (catVocab.isNotEmpty()) {
                    _vocabularyByCategory.add(Pair(category, catVocab))
                }
            }
            
            // Add overall
            _vocabularyByCategory.add(0, Pair("All", allVocab))
        }
    }

    fun selectCategory(category: String, selected: Boolean) {
        viewModelScope.launch {
            if (selected) {
                _selectedCategory.add(category)
            } else {
                _selectedCategory.remove(category)
            }
        }
    }

    fun getSelectedVocabulary(): List<Vocabulary> {
        if (_selectedCategory.isEmpty() || _selectedCategory.contains("All")) {
            return _vocabularyByCategory.flatten().flatMap { it.second }
        }
        return _vocabularyByCategory.filter { pair -> 
            _selectedCategory.contains(pair.first) 
        }.flatMap { it.second }
    }

    companion object {
        fun create(repository: JapaneseLearningRepository): Factory = Factory(repository)
    }

    class Factory(private val repository: JapaneseLearningRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LearnVocabViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LearnVocabViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}