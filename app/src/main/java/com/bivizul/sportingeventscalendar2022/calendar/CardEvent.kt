package com.bivizul.sportingeventscalendar2022.calendar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bivizul.sportingeventscalendar2022.model.Event

@Composable
fun CardEvent(
    scrollStateCard: ScrollState = rememberScrollState(),
    event: Event,
) {
    Surface(
        modifier = Modifier,
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollStateCard),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = event.eventTitle,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h4,
            )
            Text(
                text = event.eventInfo.title,
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.h5,
            )
            if (event.eventInfo.image.isNotEmpty()) {
                AsyncImage(
                    model = event.eventInfo.image,
                    contentDescription = event.eventTitle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = event.eventInfo.subtitle1,
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(10.dp))
            Text(
                text = event.eventInfo.subtitle2,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                style = MaterialTheme.typography.h6,
            )
        }
    }
}