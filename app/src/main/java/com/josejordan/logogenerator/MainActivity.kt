package com.josejordan.logogenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.josejordan.logogenerator.ui.theme.LogoGeneratorTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.josejordan.logogenerator.ui.LogoGeneratorViewModel
import com.josejordan.logogenerator.ui.content.DataColumn
import com.josejordan.logogenerator.ui.content.GeneratorColumn
import com.josejordan.logogenerator.ui.content.InfoColumn


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogoGeneratorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content() {

    var games by remember { mutableStateOf("") }
    var elements by remember { mutableStateOf("") }
     val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "LogoGenerator") })
        }
    ) { padding ->
        //Bloque Principal
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            //Bloque 1
            DataColumn(
                games,
                elements,
                onGamesChange = { games = it },
                onElementsChange = { elements = it }
            )
            //Bloque 2
            InfoColumn(LocalContext.current, LogoGeneratorViewModel())

            //Bloque 3
            GeneratorColumn()
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    LogoGeneratorTheme {
        Content()
    }
}