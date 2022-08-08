package com.bivizul.sportingeventscalendar2022.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import com.bivizul.sportingeventscalendar2022.R
import java.util.*

object Util {

    fun getPosil(context: Context): Locale = context.resources.configuration.locales[0]

    fun checkNet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun getDialogErrorConnect(context: Context, activity: Activity) {
        AlertDialog.Builder(context).apply {
            setTitle(context.getString(R.string.error_title))
            setMessage(context.getString(R.string.error_message))

            setPositiveButton("go out") { _, _ ->
                System.exit(0)
            }
            setCancelable(true)
        }.create().show()
    }
}