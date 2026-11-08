package com.japaneselearning.ui.learn

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.japaneselearning.data.model.HiraganaCharacter
import com.japaneselearning.data.model.KatakanaCharacter
import com.japaneselearning.data.model.KanjiCharacter
import com.japaneselearning.ui.components.CharacterCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnCharactersScreen(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    hiraganaCharacters: List<HiraganaCharacter>,
    katakanaCharacters: List<KatakanaCharacter>,
    kanjiCharacters: List<KanjiCharacter>,
    onCharacterClick: (type: String, id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf("Hiragana", "Katakana", "Kanji")
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text("${selectedTab} Characters")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = tabs.indexOf(selectedTab),
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                modifier = Modifier.scrollable(
                    enabled = true,
                    direction = androidx.compose.ui.geometry.Direction.RightToEnd
                )
            ) {
                tabs.forEach { tab ->
                    Tab(
                        text = { Text(tab) },
                        selected = tabs.indexOf(tab) == tabs.indexOf(selectedTab),
                        onClick = { onTabSelected(tab) },
                        selectedIcon = {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.rotate(0f)
                            )
                        }
                    )
                }
            }
            
            AnimatedVisibility(
                visible = selectedTab == "Hiragana",
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(hiraganaCharacters) { character ->
                        CharacterCard(
                            character = character,
                            onClick = { onCharacterClick("hiragana", character.id) }
                        )
                    }
                }
            }
            
            AnimatedVisibility(
                visible = selectedTab == "Katakana",
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(katakanaCharacters) { character ->
                        CharacterCard(
                            character = character,
                            onClick = { onCharacterClick("katakana", character.id) }
                        )
                    }
                }
            }
            
            AnimatedVisibility(
                visible = selectedTab == "Kanji",
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(kanjiCharacters) { character ->
                        KanjiCard(
                            character = character,
                            onClick = { onCharacterClick("kanji", character.id) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KanjiCard(
    character: KanjiCharacter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = character.character,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Stroke count: ${character.strokeCount}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Onyomi: ${character.onyomi.joinToString(", ")}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Kunyomi: ${character.kunyomi.joinToString(", ")}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Meaning: ${character.meanings.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}