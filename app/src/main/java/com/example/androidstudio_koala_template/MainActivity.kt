package com.example.androidstudio_koala_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    // Iconos
    val iconOptions = listOf(
        "Add" to Icons.Default.Add,
        "Call" to Icons.Default.Call,
        "Email" to Icons.Default.Email,
        "Favorite" to Icons.Default.Favorite,
        "Home" to Icons.Default.Home,
        "Info" to Icons.Default.Info,
        "Person" to Icons.Default.Person,
        "Search" to Icons.Default.Search,
        "Settings" to Icons.Default.Settings,
        "Share" to Icons.Default.Share
    )

    var selectedIconName by remember { mutableStateOf(iconOptions[0].first) }
    var sliderValue by remember { mutableStateOf(3f) }
    var minValue by remember { mutableStateOf(0f) }
    var maxValue by remember { mutableStateOf(10f) }
    var expanded by remember { mutableStateOf(false) }

    var finalIconName by remember { mutableStateOf(iconOptions[0].first) }
    var finalSliderValue by remember { mutableStateOf(3) }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reto 1",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Menu desplegable para seleccionar el icono
        Box {
            TextField(
                value = selectedIconName,
                onValueChange = {},
                label = { Text("Escoge uno de los siguientes iconos") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                iconOptions.forEach { (name, _) ->
                    DropdownMenuItem(
                        text = { Text(name) },
                        onClick = {
                            selectedIconName = name
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Slider con el min a max
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text("Mínimo:")
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
                Text("Maximo:")
                OutlinedTextField(
                    value = maxValue.toString(),
                    onValueChange = { maxValue = it.toFloatOrNull() ?: maxValue },
                    modifier = Modifier.width(60.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Slider para seleccionar min a max
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = minValue..maxValue, // Usamos los valores min y max proporcionados por el usuario
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de ENVIAR
        Button(onClick = {
            finalIconName = selectedIconName
            finalSliderValue = sliderValue.toInt()
        }) {
            Text("Enviar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar el icono seleccionado con badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            val selectedIcon = iconOptions.find { it.first == finalIconName }?.second ?: Icons.Default.Help

            // BadgedBox con el valor del slider
            BadgedBox(
                modifier = Modifier.padding(16.dp),
                badge = {
                    Badge {
                        Text(finalSliderValue.toString()) // Aquí mostramos el valor final del slider (que se modifica segun quiera el usuario en el slider)
                    }
                }
            ) {
                Icon(
                    imageVector = selectedIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = finalSliderValue.toString(),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}