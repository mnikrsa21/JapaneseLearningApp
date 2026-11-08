package com.japaneselearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.japaneselearning.ui.theme.JapaneseAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JapaneseAppTheme {
                JapaneseLearningApp()
            }
        }
    }
}

@Composable
fun JapaneseLearningApp() {
    AppNavigator()
}

@Preview(showBackground = true)
@Composable
fun PreviewJapaneseLearningApp() {
    JapaneseAppTheme {
        JapaneseLearningApp()
    }
}