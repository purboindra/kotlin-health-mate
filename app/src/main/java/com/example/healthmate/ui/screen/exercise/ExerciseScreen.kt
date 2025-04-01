package com.example.healthmate.ui.screen.exercise

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.permission.HealthPermission.Companion.PERMISSION_READ_HEALTH_DATA_IN_BACKGROUND
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.util.HorizontalSpacer
import com.example.healthmate.util.VerticalSpacer

data class WeightData(
    val kg: String
)

@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    healthConnectManager: HealthConnectManager
) {
    
    val coroutineScope = rememberCoroutineScope()
    val weight by exerciseViewModel.weightQuery.collectAsState()
    val weightList by exerciseViewModel.weightList.collectAsState()
    var loading by remember { mutableStateOf(false) }
    val backgroundReadAvailable by exerciseViewModel.backgroundReadAvailable.collectAsState()
    
    val permissionLauncher = rememberLauncherForActivityResult(
        healthConnectManager.requestPermissionsActivityContract()
    ) {
        Log.d("HealthConnect", "Permissions granted successfully!")
    }
    
    LaunchedEffect(Unit) {
        loading = true
        exerciseViewModel.getWeight()
        loading = false
    }
    
    LazyColumn(modifier = Modifier.safeContentPadding()) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = weight,
                    onValueChange = {
                        exerciseViewModel.onWeightChange(it)
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
                    exerciseViewModel.writeWeightInput()
                }) {
                    Text("Add")
                }
            }
            10.VerticalSpacer()
            if (loading) {
                CircularProgressIndicator()
            } else Column {
                if (weightList.isEmpty()) Text("No data")
                else weightList.forEachIndexed { index, weightRecord ->
                    val time = exerciseViewModel.formatInstantToDate(
                        weightRecord.time,
                        pattern = "E, MMM dd yyyy HH:mm:ss a",
                        timeZone = "Asia/Jakarta"
                    )
                    Text("${"%.1f".format(weightRecord.weight.inKilograms)} kilograms $time")
                }
                if (!backgroundReadAvailable) ElevatedButton(
                    onClick = {
                        permissionLauncher.launch(
                            setOf(PERMISSION_READ_HEALTH_DATA_IN_BACKGROUND)
                        )
                    }
                ) {
                    Text("Request Background Read")
                }
            }
        }
    }
}