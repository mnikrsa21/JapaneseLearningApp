package com.japaneselearning.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.japaneselearning.data.model.*
import com.japaneselearning.data.repository.JapaneseLearningRepository
import kotlinx.coroutines.launch

class LearnVerbViewModel(
    private val repository: JapaneseLearningRepository
) : ViewModel() {
    
    private val _godanVerbs = mutableStateListOf<Verb>()
    val godanVerbs: List<Verb> = _godanVerbs

    private val _ichidanVerbs = mutableStateListOf<Verb>()
    val ichidanVerbs: List<Verb> = _ichidanVerbs

    private val _irregularVerbs = mutableStateListOf<Verb>()
    val irregularVerbs: List<Verb> = _irregularVerbs

    init {
        loadVerbs()
    }

    private fun loadVerbs() {
        viewModelScope.launch {
            val allVerbs = repository.getAllVerbs()
            _godanVerbs.addAll(allVerbs.filter { it.type == "godan" })
            _ichidanVerbs.addAll(allVerbs.filter { it.type == "ichidan" })
            _irregularVerbs.addAll(allVerbs.filter { it.type == "irregular" })
        }
    }

    companion object {
        fun create(repository: JapaneseLearningRepository): Factory = Factory(repository)
    }

    class Factory(private val repository: JapaneseLearningRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LearnVerbViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LearnVerbViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}