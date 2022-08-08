package com.bivizul.sportingeventscalendar2022.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.bivizul.sportingeventscalendar2022.beforemain.BeforeMainViewModel
import com.bivizul.sportingeventscalendar2022.model.Langag
import com.bivizul.sportingeventscalendar2022.navigation.NavGraph
import com.bivizul.sportingeventscalendar2022.ui.theme.SportingEventsCalendar2022Theme
import com.bivizul.sportingeventscalendar2022.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportingEventsCalendar2022Theme {
                val beforeMainViewModel = hiltViewModel<BeforeMainViewModel>()
                if (Util.checkNet(this)) {
                    try {
                        beforeMainViewModel.getPosil(Langag(Util.getPosil(this)))
                    } catch (e: Exception) {
                        Util.getDialogErrorConnect(this, this)
                    }
                    NavGraph(
                        beforeMainViewModel = beforeMainViewModel,
                    )
                } else {
                    Util.getDialogErrorConnect(this, this)
                }
            }
        }
    }
}
