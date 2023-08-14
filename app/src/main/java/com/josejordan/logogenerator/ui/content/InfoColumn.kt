package com.josejordan.logogenerator.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.josejordan.logogenerator.ui.component.ActionButton
import com.josejordan.logogenerator.ui.component.TitleText

@Composable
fun InfoColumn() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TitleText(text = "2. Introduce Información adicional")
        ActionButton(
            text = "Iniciar Grabación",
            icon = Icons.Filled.Mic,
            description = "Iniciar Grabación"
        ) {
            //Click del boton
        }
        ActionButton(
            text = "Resumir",
            icon = Icons.Filled.Compress,
            description = "Resume la grabación"
        ) {
            //Click del boton
        }
    }
}