package com.josejordan.logogenerator.ui.content

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.josejordan.logogenerator.ui.LogoGeneratorViewModel
import com.josejordan.logogenerator.ui.component.ActionButton
import com.josejordan.logogenerator.ui.component.TitleText

@Composable
fun GeneratorColumn(context: Context, viewModel: LogoGeneratorViewModel, games: String, elements: String) {
    var imageURL by remember { mutableStateOf("") }
    val clipboard = LocalClipboardManager.current

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TitleText(text = "3. Genera el logo")
        ActionButton(
            text = "Generar",
            icon = Icons.Filled.Draw,
            description = "Genera el logotipo",
            enabled = games.isNotEmpty() && elements.isNotEmpty()
        ) {
            //Click del boton
            viewModel.generateLogo(games, elements) {
                imageURL = it
            }
        }
        if (imageURL.isNotEmpty()) {
            AsyncImage(
                model = imageURL,
                contentDescription = "$games logo",
                modifier = Modifier.fillMaxSize()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(onClick = {
                    clipboard.setText(AnnotatedString(imageURL))
                    Toast.makeText(context,"URL COPIADA", Toast.LENGTH_SHORT).show()
                }) {
                  Icon(
                        Icons.Filled.ContentCopy,
                        contentDescription = "Copiar URL",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }
            Text(text = "Copiar imagen")
        }
    }
}