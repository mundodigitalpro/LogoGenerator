package com.josejordan.logogenerator.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.josejordan.logogenerator.ui.component.DataTextField
import com.josejordan.logogenerator.ui.component.TitleText

@Composable
fun DataColumn(
    games: String,
    elements: String,
    onGamesChange: (String) -> Unit,
    onElementsChange: (String) -> Unit
) {

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        TitleText(text = "1. Completa los datos")

        DataTextField(label = "Videojuego de referencia", text = games, onValueChange = onGamesChange)
        DataTextField(label = "Elemento de referencia", text = elements, onValueChange = onElementsChange)

    }
}