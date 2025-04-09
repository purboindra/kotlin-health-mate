package com.example.healthmate.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PrimaryTextTabs(
    onTabSelect: (Int) -> Unit,
    tabSelectedIndex: Int
) {
    val titles = listOf("Hari", "Minggu", "Bulan")
    PrimaryTabRow(selectedTabIndex = tabSelectedIndex) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = tabSelectedIndex == index,
                onClick = {
                    onTabSelect(index)
                },
                text = {
                    Text(
                        text = title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
//    }
//    Column {
//        PrimaryTabRow(selectedTabIndex = state) {
//            titles.forEachIndexed { index, title ->
//                Tab(
//                    selected = state == index,
//                    onClick = { state = index },
//                    text = {
//                        Text(
//                            text = title,
//                            maxLines = 2,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                    }
//                )
//            }
//        }
//        Text(
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            text = "Primary tab ${state + 1} selected",
//            style = MaterialTheme.typography.bodyLarge
//        )
//    }
}