package com.example.androidstudio_koala_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Help
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStudioKoalaTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    var selectedIcon by remember { mutableStateOf("Add") }
    var sliderValue by remember { mutableStateOf(3f) }
    var minValue by remember { mutableStateOf(0f) }
    var maxValue by remember { mutableStateOf(10f) }
    var expanded by remember { mutableStateOf(false) }

    // Guarda el hacer clic en "Enviar"
    var finalIcon by remember { mutableStateOf("Add") }
    var finalSliderValue by remember { mutableStateOf(3) }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Intro con título superior del reto
        Text(
            text = "Reto 1 {Xavier Moreno}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // El menu hacia abajo
        Box {
            TextField(
                value = selectedIcon,
                onValueChange = {},
                label = { Text("Tria un Icon") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOf("Add", "Call", "Email").forEach { icon ->
                    DropdownMenuItem(
                        text = { Text(icon) },
                        onClick = {
                            selectedIcon = icon
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Slider con el minimo y el maximo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text("Min:")
                OutlinedTextField(
                    value = minValue.toString(),
                    onValueChange = { minValue = it.toFloatOrNull() ?: minValue },
                    modifier = Modifier.width(60.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text("Max:")
                OutlinedTextField(
                    value = maxValue.toString(),
                    onValueChange = { maxValue = it.toFloatOrNull() ?: maxValue },
                    modifier = Modifier.width(60.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Slider del max-min
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = minValue..maxValue,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Boton de enviar
        Button(onClick = {
            finalIcon = selectedIcon
            finalSliderValue = sliderValue.toInt()
        }) {
            Text("Enviar") //Revisar que envie bien el icono
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Icono seleccionado final y valor del slider como badged
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(
                imageVector = when (finalIcon) {
                    "Add" -> Icons.Default.Add
                    "Call" -> Icons.Default.Call
                    "Email" -> Icons.Default.Email
                    else -> Icons.Default.Help
                },
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = finalSliderValue.toString(),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}