package com.bivizul.sportingeventscalendar2022.beforemain

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.bivizul.sportingeventscalendar2022.navigation.Routes
import com.bivizul.sportingeventscalendar2022.ui.WebActivity
import com.bivizul.sportingeventscalendar2022.util.KEY_OUT_RESPONSE
import com.bivizul.sportingeventscalendar2022.util.Resource
import com.bivizul.sportingeventscalendar2022.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun BeforeMain(
    navController: NavController,
    beforeMainViewModel: BeforeMainViewModel,
) {

    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val resource by beforeMainViewModel.posil.collectAsState()

    LaunchedEffect(key1 = null) {
        Log.e("qwer","Before loading")
        CoroutineScope(Dispatchers.Main).launch {
            when (resource) {
                is Resource.Loading -> {Log.e("qwer","Loading")}
                is Resource.Success -> {
                    resource.data?.let {
                        Log.e("qwer","Success")
                        Log.e("qwer :"," posil : $it")
                        if (it.posil.length > 2) {
                            delay(1000)
                            val intent = Intent(activity, WebActivity::class.java)
                            intent.putExtra(KEY_OUT_RESPONSE, it.posil)
                            startActivity(context, intent, bundleOf())
                        } else {
                            delay(1000)
                            navController.navigate(Routes.Main.route)
                        }
                    }
                }
                is Resource.Error -> {
                    Log.e("qwer","Error")
                    Util.getDialogErrorConnect(context, activity)
                }
            }
        }
    }

    BeforeMainScreen()

}

@Composable
fun BeforeMainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.surface,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}


