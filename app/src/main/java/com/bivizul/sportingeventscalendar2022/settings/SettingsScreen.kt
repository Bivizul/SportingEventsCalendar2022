package com.bivizul.sportingeventscalendar2022.settings


import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bivizul.sportingeventscalendar2022.R
import com.bivizul.sportingeventscalendar2022.util.CustSwitch

@Composable
fun SettingsScreen() {

    val checkedState = remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.settings),
                modifier = Modifier
                    .padding(16.dp),
                style = MaterialTheme.typography.h4
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.notifications),
                    style = MaterialTheme.typography.h5
                )
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    CustSwitch(
                        isChecked = checkedState.value,
                        onCheckChanged = {
                            checkedState.value = it
                        }
                    )
                }
            }
        }
    }
}