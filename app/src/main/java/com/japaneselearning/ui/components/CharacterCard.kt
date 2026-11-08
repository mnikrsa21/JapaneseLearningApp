package com.japaneselearning.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.japaneselearning.data.model.HiraganaCharacter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiraganaCard(
    character: HiraganaCharacter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = remember { LocalHapticFeedback.current() }
    
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                onClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(300)) + scaleIn(animationSpec = tween(300))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = character.character,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontFamily = FontFamily.Default,
                            color = MaterialTheme.colorScheme.primary,
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.1f),
                                offset = Offset(2f, 2f),
                                blurRadius = 4f
                            )
                        )
                    )
                    Text(
                        text = character.romaji,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "例:",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = character.examples.take(2).joinToString(" "),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterPager(
    items: List<HiraganaCharacter>,
    modifier: Modifier = Modifier,
    itemContent: @Composable (HiraganaCharacter) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
    
    HorizontalPager(
        pageCount = items.size,
        state = pagerState,
        modifier = modifier
    ) { page ->
        val animatedScale by animateFloatAsState(
            targetValue = if (pagerState.currentPage == page) 1.0f else 0.9f,
            animationSpec = tween(300)
        )
        val animatedAlpha by animateFloatAsState(
            targetValue = if (pagerState.currentPage == page) 1f else 0.5f,
            animationSpec = tween(300)
        )
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = animatedScale
                    scaleY = animatedScale
                    alpha = animatedAlpha
                }
        ) {
            itemContent(items[page])
        }
    }
}

@Composable
fun AnimatedText(
    text: String,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.headlineLarge
) {
    var key by remember { mutableStateOf(0) }
    
    LaunchedEffect(text) {
        key++
    }
    
    AnimatedTextContent(content = text, key = key, modifier = modifier, style = style)
}

@Composable
private fun AnimatedTextContent(
    content: String,
    key: Any,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + slideInHorizontally(),
        exit = fadeOut() + slideOutHorizontally(),
        modifier = modifier
    ) {
        Text(
            text = content,
            style = style
        )
    }
}