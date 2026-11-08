# Japanese Learning App

A comprehensive Android app for learning Japanese characters (Hiragana, Katakana, Kanji), vocabulary, verbs, and writing practice.

## Features

- **Learn Hiragana** - All 46 basic characters with stroke order and examples
- **Learn Katakana** - All 46 basic characters with stroke order and examples  
- **Learn Kanji** - 100+ essential kanji with meanings and readings
- **Vocabulary** - 100+ common words organized by category
- **Verbs** - 25+ verbs with full conjugation tables (Godan, Ichidan, Irregular)
- **Writing Practice** - Interactive canvas with stroke-by-stroke guidance
- **Test Mode** - Multiple quiz types with progress tracking
- **Spaced Repetition** - Review system based on mastery level
- **Progress Tracking** - Detailed statistics and test history

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose with Material 3
- **Database**: Room Persistence Library
- **Architecture**: MVVM
- **Navigation**: Jetpack Navigation Compose
- **Animation**: Smooth transitions with Compose animations
- **CI/CD**: GitHub Actions

## Project Structure

```
app/src/main/java/com/japaneselearning/
├── MainActivity.kt
├── AppNavigator.kt
├── data/
│   ├── model/Entities.kt
│   ├── database/AppDatabase.kt
│   ├── dao/
│   │   ├── HiraganaDao.kt
│   │   ├── KatakanaDao.kt
│   │   ├── KanjiDao.kt
│   │   ├── VocabularyDao.kt
│   │   ├── VerbDao.kt
│   │   ├── TestHistoryDao.kt
│   │   ├── UserProgressDao.kt
│   │   └── ReviewItemDao.kt
│   └── repository/LearningRepository.kt
├── ui/
│   ├── theme/
│   │   ├── Theme.kt
│   │   └── Typography.kt
│   ├── components/
│   │   └── CharacterCard.kt
│   ├── home/
│   │   └── HomeScreen.kt
│   ├── learn/
│   │   ├── LearnCharactersScreen.kt
│   │   ├── LearnVocabScreen.kt
│   │   └── LearnVerbScreen.kt
│   ├── test/
│   │   └── TestScreen.kt
│   └── writing/
│       └── WritingScreen.kt
└── viewmodel/
    ├── HomeViewModel.kt
    ├── LearnCharactersViewModel.kt
    ├── TestViewModel.kt
    ├── WritingViewModel.kt
    ├── LearnVocabViewModel.kt
    └── LearnVerbViewModel.kt
```

## Building

```bash
./gradlew assembleDebug
./gradlew assembleRelease
```

## Testing

```bash
./gradlew test
```

## CI/CD

The project uses GitHub Actions for automated builds:
- Runs on push to main/develop branches
- Builds Debug and Release APKs
- Runs Lint checks
- Uploads artifacts

## Features for Smooth UI

- **Compose Animations**: fadeIn, slideIn transitions for smooth screen changes
- **Pager Animations**: Accompanist pager with scale and alpha animations
- **Card Animations**: animateContentSize for smooth expansion
- **Haptic Feedback**: Tactile feedback for interactions
- **Optimized Rendering**: Flow-based data streams with StateFlow