package com.japaneselearning.ui.test

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3 Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.japaneselearning.ui.components.ResultDisplay
import com.japaneselearning.ui.components.NavigationButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
    testType: String,
    currentQuestion: Int,
    totalQuestions: Int,
    question: String,
    answer: String,
    options: List<String>,
    onAnswerSelected: (String) -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    onSubmit: () -> Unit,
    isCorrect: Boolean?,
    score: Int,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(when (testType) {
                        "hiragana" -> "Hiragana Test"
                        "katakana" -> "Katakana Test"
                        "kanji" -> "Kanji Test"
                        "vocabulary" -> "Vocabulary Test"
                        "verbs" -> "Verb Test"
                        else -> "Test"
                    })
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            LinearProgressIndicator(
                progress = currentQuestion.toFloat() / totalQuestions.toFloat(),
                modifier = Modifier.fillMaxWidth()
            )
            
            Text(
                text = "Question $currentQuestion of $totalQuestions",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = question,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            
            MultiChoiceOptions(
                options = options,
                onSelect = { onAnswerSelected(it) },
                enabled = isCorrect == null
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            if (isCorrect != null) {
                ResultDisplay(
                    isCorrect = isCorrect,
                    score = score,
                    totalQuestions = totalQuestions
                )
            }
            
            NavigationButtons(
                currentQuestion = currentQuestion,
                totalQuestions = totalQuestions,
                isCorrect = isCorrect,
                onPrevious = onPrevious,
                onNext = onNext,
                onSubmit = onSubmit
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiChoiceOptions(
    options: List<String>,
    onSelect: (String) -> Unit,
    enabled: Boolean
) {
    LazyColumn(
        modifier = Modifier.fillMaxParent()
    ) {
        items(options) { option ->
            OutlinedButton(
                onClick = { if (enabled) onSelect(option) },
                enabled = enabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(option)
            }
        }
    }
}