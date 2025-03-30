package com.example.healthmate.ui.screen.exercise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.records.WeightRecord
import androidx.navigation.NavHostController
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.util.HorizontalSpacer
import com.example.healthmate.util.VerticalSpacer
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

data class WeightData(
    val kg: String
)

@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    healthConnectManager: HealthConnectManager
) {
    
    val coroutineScope = rememberCoroutineScope()
    
    var weight by remember { mutableStateOf("") }
    var weightList by remember { mutableStateOf<List<WeightRecord>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    
    
    fun addWeight() {
        loading = true
        coroutineScope.launch {
            healthConnectManager.writeWeightInput(weight.toDouble())
            val start = ZonedDateTime.now().toInstant()
            val end = ZonedDateTime.now().toInstant()
            weightList =
                healthConnectManager.readWeightInputs(start = start, end = end)
        }
        loading = false
    }
    
    LaunchedEffect(Unit) {
        loading = true
        val start = ZonedDateTime.now().toInstant()
        val end = ZonedDateTime.now().toInstant()
        healthConnectManager.readWeightInputs(start = start, end = end)
        loading = false
    }
    
    LazyColumn(modifier = Modifier.safeContentPadding()) {
        item {
            Row (verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = weight,
                    onValueChange = {
                        weight = it
                    },
                    placeholder = {
                        Text("Weight")
                    },
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = GrayDark,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                8.HorizontalSpacer()
                ElevatedButton(onClick = {
                    addWeight()
                }) {
                    Text("Add")
                }
            }
            10.VerticalSpacer()
            if (loading) {
                CircularProgressIndicator()
            } else Column {
                if (weightList.isEmpty()) Text("No data")
                else weightList.forEachIndexed { index, weightRecord -> Text("Kg: ${weightRecord.weight}") }
            }
        }
    }
}