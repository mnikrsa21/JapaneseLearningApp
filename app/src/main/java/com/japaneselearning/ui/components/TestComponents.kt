package com.japaneselearning.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.material3 Icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultDisplay(
    isCorrect: Boolean,
    score: Int,
    totalQuestions: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(300)),
        colors = CardDefaults.cardColors(
            containerColor = if (isCorrect) 
                MaterialTheme.colorScheme.successContainer 
            else 
                MaterialTheme.colorScheme.errorContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = if (isCorrect) "Correct!" else "Incorrect!",
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isCorrect) 
                        MaterialTheme.colorScheme.success 
                    else 
                        MaterialTheme.colorScheme.error
                )
                Text(
                    text = "Score: $score / $totalQuestions",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationButtons(
    currentQuestion: Int,
    totalQuestions: Int,
    isCorrect: Boolean?,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onSubmit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { 
                if (currentQuestion > 1) onPrevious() 
            },
            enabled = currentQuestion > 1
        ) {
            Text("Previous")
        }
        
        Button(
            onClick = {
                if (isCorrect != null) {
                    onNext()
                } else {
                    onSubmit()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isCorrect != null) 
                    MaterialTheme.colorScheme.primary 
                else 
                    MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(if (isCorrect != null) "Next" else "Submit")
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
            .animateContentSize(animationSpec = tween(300)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Book,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.labelMedium)
                Text(text = value, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}