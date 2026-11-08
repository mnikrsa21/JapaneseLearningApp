package com.japaneselearning.ui.verbs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.japaneselearning.data.model.Verb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbScreen(
    onVerbClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val verbs = remember { mutableStateListOf<Verb>() }
    
    LaunchedEffect(true) {
        verbs.addAll(listOf(
            Verb(1, "する", "suru", "to do", "irregular", emptyList(), emptyList()),
            Verb(2, "くる", "kuru", "to come", "irregular", emptyList(), emptyList()),
            Verb(3, "あれる", "areru", "to exist", "irregular", emptyList(), emptyList()),
            Verb(4, "いる", "iru", "to be (animate)", "irregular", emptyList(), emptyList()),
            Verb(5, "よむ", "yomu", "to read", "ichidan", emptyList(), emptyList()),
            Verb(6, "きく", "kiku", "to ask", "ichidan", emptyList(), emptyList()),
            Verb(7, "たべる", "taberu", "to eat", "ichidan", emptyList(), emptyList()),
            Verb(8, "のむ", "nomu", "to drink", "ichidan", emptyList(), emptyList()),
            Verb(9, "あける", "akeru", "to open", "godan", emptyList(), emptyList()),
            Verb(10, "あまる", "amaru", "to settle", "godan", emptyList(), emptyList()),
            Verb(11, "きる", "kiru", "to buy", "godan", emptyList(), emptyList()),
            Verb(12, "くれる", "kureru", "to give", "godan", emptyList(), emptyList())
        ))
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Japanese Verbs") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TabsContent(verbs) { verb, onClick ->
                VerbCard(verb = verb, onClick = { onClick(verb.id) })
            }
        }
    }
}

@Composable
private fun TabsContent(
    items: List<Verb>,
    itemContent: @Composable (Verb, (Int) -> Unit) -> Unit
) {
    var selectedTab by remember { mutableStateOf("Godan") }
    val tabs = listOf("Godan", "Ichidan", "Irregular")
    
    Column {
        TabRow(selectedTabIndex = tabs.indexOf(selectedTab)) {
            tabs.forEach { tab ->
                Tab(
                    text = { Text(tab) },
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab }
                )
            }
        }
        
        val filteredVerbs = when (selectedTab) {
            "Godan" -> items.filter { it.type == "godan" }
            "Ichidan" -> items.filter { it.type == "ichidan" }
            "Irregular" -> items.filter { it.type == "irregular" }
            else -> items
        }
        
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(filteredVerbs) { verb ->
                VerbCard(
                    verb = verb,
                    onClick = { /* navigate */ }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbCard(
    verb: Verb,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = verb.verb,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = verb.reading,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = verb.meaning,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Type: ${verb.type}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}