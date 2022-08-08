package com.bivizul.sportingeventscalendar2022.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.bivizul.sportingeventscalendar2022.R
import com.bivizul.sportingeventscalendar2022.navigation.Routes

@Composable
fun MainScreen(
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )
            Button(onClick = { navController.navigate(Routes.Calendar.route) }) {
                Text(
                    text = "Calendar",
                    style = MaterialTheme.typography.h4
                )
            }
            Button(onClick = { navController.navigate(Routes.Settings.route) }) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.h5
                )
            }

        }
    }
}

