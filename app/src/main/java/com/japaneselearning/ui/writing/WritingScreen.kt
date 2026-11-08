package com.japaneselearning.ui.writing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3 Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.japaneselearning.viewmodel.CharacterInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingScreen(
    characterInfo: CharacterInfo?,
    onComplete: (Boolean) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strokeWidth = 8f
    val strokeColor = Color(0xFF2196F3)
    var paths by remember { mutableStateOf(listOf<StrokedPath>()) }
    var currentPathPoints by remember { mutableStateOf<MutableList<Offset>?>(null) }
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Writing Practice") },
                navigationIcon = {
                    IconButton(onClick = { onCancel() }) {
                        Icon(Icons.Default.Close, contentDescription = "Cancel")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onComplete(true)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Check, contentDescription = "Submit")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (characterInfo != null) {
                Text(
                    text = "Learn: ${characterInfo.character}",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                
                DrawingCanvas(
                    paths = paths,
                    currentPathPoints = currentPathPoints,
                    strokeWidth = strokeWidth,
                    strokeColor = strokeColor,
                    onPointAdd = { offset ->
                        if (currentPathPoints == null) {
                            currentPathPoints = mutableListOf(offset)
                        } else {
                            currentPathPoints!!.add(offset)
                        }
                    },
                    onDrawComplete = {
                        if (currentPathPoints != null && currentPathPoints!!.isNotEmpty()) {
                            paths = paths + StrokedPath(
                                points = currentPathPoints!!.toList(),
                                color = strokeColor,
                                width = strokeWidth
                            )
                            currentPathPoints = null
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Romaji: ${characterInfo.reading}",
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                Text(
                    text = "Loading character...",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun DrawingCanvas(
    paths: List<StrokedPath>,
    currentPathPoints: MutableList<Offset>?,
    strokeWidth: Float,
    strokeColor: Color,
    onPointAdd: (Offset) -> Unit,
    onDrawComplete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color(0xFFF5F5F5))
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        onPointAdd(offset)
                        onDrawComplete()
                    }
                )
            }
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            paths.forEach { stroke ->
                if (stroke.points.size > 1) {
                    val path = Path().apply {
                        moveTo(stroke.points.first().x, stroke.points.first().y)
                        stroke.points.drop(1).forEach { point ->
                            lineTo(point.x, point.y)
                        }
                    }
                    drawPath(
                        path = path,
                        color = stroke.color,
                        style = Stroke(stroke.width, cap = StrokeCap.Round)
                    )
                }
            }
            
            currentPathPoints?.let { points ->
                if (points.size > 1) {
                    val path = Path().apply {
                        moveTo(points.first().x, points.first().y)
                        points.drop(1).forEach { point ->
                            lineTo(point.x, point.y)
                        }
                    }
                    drawPath(
                        path = path,
                        color = strokeColor,
                        style = Stroke(strokeWidth, cap = StrokeCap.Round)
                    )
                }
            }
        }
    }
}

data class StrokedPath(
    val points: List<Offset>,
    val color: Color,
    val width: Float
)