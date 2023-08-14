package com.josejordan.logogenerator.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Draw
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.josejordan.logogenerator.ui.component.ActionButton
import com.josejordan.logogenerator.ui.component.TitleText

@Composable
fun GeneratorColumn() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TitleText(text = "3. Genera el logo")
        ActionButton(
            text = "Generar",
            icon = Icons.Filled.Draw,
            description = "Genera el logotipo"
        ) {
            //Click del boton
        }
    }
}