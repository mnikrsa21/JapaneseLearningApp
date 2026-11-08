package com.japaneselearning.data.util

import com.japaneselearning.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SampleData {
    val hiragana = listOf(
        HiraganaCharacter(1, "あ", "a", "stroke_1", listOf("あま", "あの")),
        HiraganaCharacter(2, "い", "i", "stroke_2", listOf("いぬ", "いま")),
        HiraganaCharacter(3, "う", "u", "stroke_3", listOf("うみ", "うつ")),
        HiraganaCharacter(4, "え", "e", "stroke_4", listOf("えき", "えほん")),
        HiraganaCharacter(5, "お", "o", "stroke_5", listOf("およむ", "おしゃれ")),
        HiraganaCharacter(6, "か", "ka", "stroke_6", listOf("かさ", "かぜ")),
        HiraganaCharacter(7, "き", "ki", "stroke_7", listOf("きれ", "きつね")),
        HiraganaCharacter(8, "く", "ku", "stroke_8", listOf("くるま", "くつ")),
        HiraganaCharacter(9, "け", "ke", "stroke_9", listOf("けしが", "けむし")),
        HiraganaCharacter(10, "こ", "ko", "stroke_10", listOf("こまつ", "こども")),
        HiraganaCharacter(11, "さ", "sa", "stroke_11", listOf("さかな", "さくら")),
        HiraganaCharacter(12, "し", "shi", "stroke_12", listOf("しんぶん", "しれい")),
        HiraganaCharacter(13, "す", "su", "stroke_13", listOf("すき", "すず")),
        HiraganaCharacter(14, "せ", "se", "stroke_14", listOf("せんせい", "せかい")),
        HiraganaCharacter(15, "そ", "so", "stroke_15", listOf("そら", "そんな")),
        HiraganaCharacter(16, "た", "ta", "stroke_16", listOf("たまご", "たん")),
        HiraganaCharacter(17, "ち", "chi", "stroke_17", listOf("ちかく", "ちいさ")),
        HiraganaCharacter(18, "つ", "tsu", "stroke_18", listOf("つくえ", "つむ")),
        HiraganaCharacter(19, "て", "te", "stroke_19", listOf("てごと", "てあらい")),
        HiraganaCharacter(20, "と", "to", "stroke_20", listOf("とけい", "となり")),
        HiraganaCharacter(21, "な", "na", "stroke_21", listOf("なまけ", "なつ")),
        HiraganaCharacter(22, "に", "ni", "stroke_22", listOf("にんじ", "にく")),
        HiraganaCharacter(23, "ぬ", "nu", "stroke_23", listOf("ぬしが", "ぬま")),
        HiraganaCharacter(24, "ね", "ne", "stroke_24", listOf("ねこ", "ねや"))
    )
    
    val katakana = listOf(
        KatakanaCharacter(1, "ア", "a", "stroke_1", listOf("アメリカ")),
        KatakanaCharacter(2, "イ", "i", "stroke_2", listOf("イッチ")),
        KatakanaCharacter(3, "ウ", "u", "stroke_3", listOf("ウェスト")),
        KatakanaCharacter(4, "エ", "e", "stroke_4", listOf("エレベーター")),
        KatakanaCharacter(5, "オ", "o", "stroke_5", listOf("オフィス")),
        KatakanaCharacter(6, "カ", "ka", "stroke_6", listOf("カフェ")),
        KatakanaCharacter(7, "キ", "ki", "stroke_7", listOf("キャロップ")),
        KatakanaCharacter(8, "ク", "ku", "stroke_8", listOf("クルー"))
    )
    
    val kanji = listOf(
        KanjiCharacter(1, "日", listOf("日"), listOf("ひ", "に", "とき"), 1, 1, listOf("日本", "日曜日")),
        KanjiCharacter(2, "本", listOf("本"), listOf("ほん", "もと"), 5, 1, listOf("日本", "本物")),
        KanjiCharacter(3, "学", listOf("学"), listOf("がく", "まなぶ"), 6, 1, listOf("学校", "研究")),
        KanjiCharacter(4, "生", listOf("生"), listOf("せい", "しょう", "いかる"), 8, 1, listOf("学生", "生きる")),
        KanjiCharacter(5, "食", listOf("食"), listOf("しょく", "たべる"), 9, 1, listOf("食事", "食べる")),
        KanjiCharacter(6, "飲", listOf("飲"), listOf("いん", "のむ"), 9, 1, listOf("飲み物", "飲む")),
        KanjiCharacter(7, "見", listOf("見"), listOf("けん", "みる"), 8, 1, listOf("見て", "見つける")),
        KanjiCharacter(8, "話", listOf("話"), listOf("わ", "はなす"), 14, 1, listOf("話す", "話題")),
        KanjiCharacter(9, "読", listOf("読"), listOf("どく", "よむ"), 11, 1, listOf("読書", "読む")),
        KanjiCharacter(10, "書", listOf("書"), listOf("しょ", "かく"), 6, 1, listOf("書類", "書く"))
    )
    
    val vocabulary = listOf(
        Vocabulary(1, "へんか", "henka", "change", "noun", listOf("設定の変更", "変更します")),
        Vocabulary(2, "けいかく", "keikaku", "plan", "noun", listOf("計画を立てる", "今日の計画")),
        Vocabulary(3, "しんしょ", "shinsyo", "book", "noun", listOf("辞書の本", "新しい本")),
        Vocabulary(4, "ほん", "hon", "book", "noun", listOf("本を読む", "私の本")),
        Vocabulary(5, "がっせい", "gassei", "success", "noun", listOf("成功した", "成功を遂げる")),
        Vocabulary(6, "しっぽ", "shippo", "tail", "noun", listOf("犬のしっぽ", "しっぽを振る")),
        Vocabulary(7, "みず", "mizu", "water", "noun", listOf("飲み水", "水を飲む")),
        Vocabulary(8, "やま", "yama", "mountain", "noun", listOf("富士山", "山を登る")),
        Vocabulary(9, "くしか", "kushika", "to write", "verb", listOf("日本語を書く", "手紙を書く")),
        Vocabulary(10, "よむ", "yomu", "to read", "ichidan verb", listOf("本を読む", "新聞を読む"))
    )
    
    val verbs = listOf(
        Verb(1, "する", "suru", "to do", "irregular", listOf("します", "した", "する"), listOf("勉強する", "本をする")),
        Verb(2, "くる", "kuru", "to come", "irregular", listOf("きます", "きた", "くる"), listOf("本をくる", "電話をくる")),
        Verb(3, "ある", "aru", "to exist", "irregular", listOf("あります", "ありました", "ある"), listOf("本がある", "時計がある")),
        Verb(4, "いる", "iru", "to exist", "irregular", listOf("います", "いました", "いる"), listOf("ペンがいる", "本がいる")),
        Verb(5, "よむ", "yomu", "to read", "ichidan", listOf("よみます", "よんだ", "よむ"), listOf("本をよむ", "新聞をよむ")),
        Verb(6, "きく", "kiku", "to ask", "ichidan", listOf("ききます", "きいた", "きく"), listOf("先生にきく", "友達にきく")),
        Verb(7, "あける", "akeru", "to open", "godan", listOf("あけます", "あけた", "あける"), listOf("ドアをあける", "本をあける")),
        Verb(8, "あまる", "amaru", "to settle", "godan", listOf("あまります", "あまった", "あまる"), listOf("雨があまる", "請求があまる")),
        Verb(9, "たべる", "taberu", "to eat", "ichidan", listOf("たべます", "たべた", "たべる"), listOf("ご飯をたべる", "料理をたべる")),
        Verb(10, "のむ", "nomu", "to drink", "ichidan", listOf("のみます", "のんだ", "のむ"), listOf("水をのむ", "コーヒーをのむ"))
    )
}

class DataLoader {
    suspend fun loadSampleData(database: com.japaneselearning.data.database.AppDatabase) {
        withContext(Dispatchers.IO) {
            database.hiraganaDao().insertAll(SampleData.hiragana)
            database.katakanaDao().insertAll(SampleData.katakana)
            database.kanjiDao().insertAll(SampleData.kanji)
            database.vocabularyDao().insertAll(SampleData.vocabulary)
            database.verbDao().insertAll(SampleData.verbs)
            database.userProgressDao().insert(com.japaneselearning.data.model.UserProgress())
        }
    }
}