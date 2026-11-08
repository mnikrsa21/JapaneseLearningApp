package com.japaneselearning.ui.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Vocabulary
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.japaneselearning.ui.components.CharacterCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToHiragana: () -> Unit,
    onNavigateToKatakana: () -> Unit,
    onNavigateToKanji: () -> Unit,
    onNavigateToVocabulary: () -> Unit,
    onNavigateToVerbs: () -> Unit,
    onNavigateToWriting: () -> Unit,
    onNavigateToTest: () -> Unit,
    totalCharacters: Int,
    kanjiCount: Int,
    vocabCount: Int,
    verbCount: Int,
    dueReviews: Int
) {
    val expanded by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Japanese Learning") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToWriting() },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Writing Practice"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Your Progress",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(animationSpec = tween(300)),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCard(
                    title = "Characters",
                    value = totalCharacters.toString(),
                    icon = Icons.Default.Book,
                    onClick = { onNavigateToHiragana() }
                )
                StatCard(
                    title = "Kanji",
                    value = kanjiCount.toString(),
                    icon = Icons.Default.List,
                    onClick = { onNavigateToKanji() }
                )
            }
            
            StatCard(
                title = "Vocabulary",
                value = vocabCount.toString(),
                icon = Icons.Default.Vocabulary,
                onClick = { onNavigateToVocabulary() },
                modifier = Modifier.padding(top = 8.dp)
            )
            
            StatCard(
                title = "Verbs",
                value = verbCount.toString(),
                icon = Icons.Default.RestartAlt,
                onClick = { onNavigateToVerbs() }
            )
            
            ExpandableCard(
                title = "Spaced Repetition",
                expanded = expanded,
                onExpandChange = { }
            ) {
                if (dueReviews > 0) {
                    Text("You have $dueReviews items due for review")
                } else {
                    Text("All items are up to date!")
                }
                Button(
                    onClick = { onNavigateToTest() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Start Review")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.titleMedium
            )
            
            ActionButtons(
                onWritingClick = { onNavigateToWriting() },
                onTestClick = { onNavigateToTest() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
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
                imageVector = icon,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    title: String,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize(animationSpec = tween(300)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                content()
            }
        }
    }
}

@Composable
fun ActionButtons(
    onWritingClick: () -> Unit,
    onTestClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = onWritingClick,
            modifier = Modifier.weight(1f)
        ) {
            Text("Writing")
        }
        Button(
            onClick = onTestClick,
            modifier = Modifier.weight(1f)
        ) {
            Text("Test")
        }
    }
}