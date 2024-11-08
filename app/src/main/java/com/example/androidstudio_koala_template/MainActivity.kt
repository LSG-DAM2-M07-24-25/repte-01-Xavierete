package com.example.componentdropdownmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.componentdropdownmenu.ui.theme.ComponentDropDownMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComponentDropDownMenuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyDropDownMenu(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenu(modifier: Modifier = Modifier) {
    var selectedText: String by remember { mutableStateOf("") }
    var expanded: Boolean by remember { mutableStateOf(false) }
    val hobbies = listOf("Play music", "Practice sport", "Programming", "Reading", "Other")

    Column(
        Modifier
            .padding(20.dp, 80.dp)
    ) {

        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                disabledBorderColor = Color.LightGray,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Yellow,
                disabledLabelColor = Color.LightGray,
                cursorColor = Color.Black
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
        ) {
            hobbies.forEach { hobby ->
                DropdownMenuItem(
                    text = { Text(text = hobby) },
                    onClick = {
                        expanded = false
                        selectedText = hobby
                    }
                )
            }
        }

        // Añadimos los Sliders debajo del DropDownMenu
        MyProSlider(modifier = Modifier.padding(top = 16.dp))
        MyRangeSlider(modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun MyProSlider(modifier: Modifier) {
    var sliderValue: Float by remember { mutableStateOf(0f) }
    var finishValue: String by remember { mutableStateOf("") }

    Column(modifier) {
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            onValueChangeFinished = { finishValue = sliderValue.toString() },
            valueRange = 0f..10f,
            steps = 9,
            enabled = true
        )

        Text(text = finishValue)
    }
}

@Composable
fun MyRangeSlider(modifier: Modifier) {
    var currentRange by remember { mutableStateOf(2f..5f) }

    Column(modifier) {
        RangeSlider(
            value = currentRange,
            onValueChange = { currentRange = it },
            valueRange = 0f..10f,
            enabled = true
        )

        Text(
            text = "From ${String.format("%.2f", currentRange.start)} to ${
                String.format("%.2f", currentRange.endInclusive)
            }"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComponentDropDownMenuTheme {
        MyDropDownMenu()
    }
}