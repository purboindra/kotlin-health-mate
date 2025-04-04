package com.example.healthmate.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.icons.MyIconPack
import com.example.healthmate.ui.icons.myiconpack.Cardio
import com.example.healthmate.ui.icons.myiconpack.Foot
import com.example.healthmate.ui.theme.Green
import com.example.healthmate.util.VerticalSpacer

@Composable
fun ActivityHealthDialogContent(
    index: Int,
) {
    
    Log.d("ActivityHealthDialogContent", "Index: $index")
    
    when (index) {
        0 -> FirstContent()
        1 -> SecondContent()
        else -> ThirdContent()
    }
}


@Composable
private fun ThirdContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Cara mudah untuk tetap sehat",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W500,
            ),
            textAlign = TextAlign.Center,
        )
        24.VerticalSpacer()
        Column {
            RowIconWithText(
                icon = MyIconPack.Cardio,
                contentDescription = "Kardio",
                text = "150",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W500
                )
            )
            Text(
                "Target mingguan",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.W500
                )
            )
        }
        24.VerticalSpacer()
        Text(
            "Tidak hanya menghitung langkah, Health Mate juga memberikan Poin Kardio saat Anda berolahraga",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal,
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SecondContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Cara mendapatkan Poin Kardio",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W500,
            ),
            textAlign = TextAlign.Center,
        )
        24.VerticalSpacer()
        Icon(
            MyIconPack.Cardio,
            contentDescription = "Kardio",
            modifier = Modifier.size(48.dp),
            tint = Green
        )
        24.VerticalSpacer()
        Text(
            "Tidak hanya menghitung langkah, Health Mate juga memberikan Poin Kardio saat Anda berolahraga",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal,
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun FirstContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Terus pantau aktivitas Anda dengan Health Mate",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W500,
            ),
            textAlign = TextAlign.Center,
        )
        24.VerticalSpacer()
        ColumnIconWithText(
            icon = MyIconPack.Cardio,
            iconDescription = "Kardio",
            title = "Poin Kardio",
            description = "Capai poin target dengan bergerak lebih cepat"
        )
        12.VerticalSpacer()
        ColumnIconWithText(
            icon = MyIconPack.Foot,
            iconDescription = "Langkah",
            title = "Langkah",
            description = "Teruslah bergerak untuk mencapai target ini"
        )
        15.VerticalSpacer()
        Text(
            "Tidak hanya menghitung langkah, Health Mate juga memberikan Poin Kardio saat Anda berolahraga",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal,
            ),
            textAlign = TextAlign.Center
        )
    }
}