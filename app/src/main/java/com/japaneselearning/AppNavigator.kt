package com.japaneselearning

import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.japaneselearning.ui.learn.LearnCharactersScreen
import com.japaneselearning.ui.test.TestScreen
import com.japaneselearning.ui.writing.WritingScreen
import com.japaneselearning.ui.vocab.VocabularyScreen
import com.japaneselearning.ui.verbs.VerbScreen
import com.japaneselearning.ui.home.HomeScreen
import com.japaneselearning.data.model.Vocabulary
import com.japaneselearning.data.model.Verb

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf("Hiragana") }
    var categories by remember { 
        mutableStateOf(setOf<String>()) 
    }
    var filteredVocab by remember {
        mutableStateOf<List<Vocabulary>>(emptyList())
    }
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToHiragana = { navController.navigate("hiragana") },
                onNavigateToKatakana = { navController.navigate("katakana") },
                onNavigateToKanji = { navController.navigate("kanji") },
                onNavigateToVocabulary = { navController.navigate("vocabulary") },
                onNavigateToVerbs = { navController.navigate("verbs") },
                onNavigateToWriting = { navController.navigate("writing") },
                onNavigateToTest = { navController.navigate("test") },
                totalCharacters = 100,
                kanjiCount = 100,
                vocabCount = 100,
                verbCount = 25,
                dueReviews = 0
            )
        }
        
        composable("hiragana") {
            LearnCharactersScreen(
                selectedTab = "Hiragana",
                onTabSelected = { selectedTab = it },
                hiraganaCharacters = emptyList(),
                katakanaCharacters = emptyList(),
                kanjiCharacters = emptyList(),
                onCharacterClick = { type, id -> navController.navigate("$type/$id") }
            )
        }
        
        composable("katakana") {
            LearnCharactersScreen(
                selectedTab = "Katakana",
                onTabSelected = { selectedTab = it },
                hiraganaCharacters = emptyList(),
                katakanaCharacters = emptyList(),
                kanjiCharacters = emptyList(),
                onCharacterClick = { type, id -> navController.navigate("$type/$id") }
            )
        }
        
        composable("kanji") {
            LearnCharactersScreen(
                selectedTab = "Kanji",
                onTabSelected = { selectedTab = it },
                hiraganaCharacters = emptyList(),
                katakanaCharacters = emptyList(),
                kanjiCharacters = emptyList(),
                onCharacterClick = { type, id -> navController.navigate("$type/$id") }
            )
        }
        
        composable("vocabulary") {
            VocabularyScreen(
                categories = listOf("All"),
                selectedCategories = categories,
                onCategorySelected = { category, selected ->
                    categories = if (selected) categories + category else categories - category
                },
                filteredVocabulary = filteredVocab,
                onVocabularyClick = { id -> navController.navigate("vocab_detail/$id") }
            )
        }
        
        composable("verbs") {
            VerbScreen(
                onVerbClick = { id -> navController.navigate("verb_detail/$id") }
            )
        }
        
        composable("writing") {
            WritingScreen(
                characterInfo = null,
                onComplete = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }
        
        composable("test") {
            TestScreen(
                testType = "hiragana",
                currentQuestion = 1,
                totalQuestions = 10,
                question = "What is this character?",
                answer = "あ",
                options = listOf("あ", "い", "う", "え"),
                onAnswerSelected = { },
                onNext = { navController.popBackStack() },
                onPrevious = { },
                onSubmit = { },
                isCorrect = null,
                score = 0
            )
        }
    }
}