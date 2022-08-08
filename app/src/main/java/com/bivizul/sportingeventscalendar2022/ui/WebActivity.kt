package com.bivizul.sportingeventscalendar2022.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import com.bivizul.sportingeventscalendar2022.R
import com.bivizul.sportingeventscalendar2022.util.DEF_OUT_RESPONSE
import com.bivizul.sportingeventscalendar2022.util.KEY_OUT_RESPONSE
import dagger.hilt.android.AndroidEntryPoint
import im.delight.android.webview.AdvancedWebView

@AndroidEntryPoint
class WebActivity : ComponentActivity(), AdvancedWebView.Listener {

    lateinit var mWebView: AdvancedWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val url = intent.getStringExtra(KEY_OUT_RESPONSE) ?: DEF_OUT_RESPONSE

        mWebView = findViewById<View>(R.id.webview) as AdvancedWebView

        mWebView.webViewClient = WebViewClient()
        mWebView.webChromeClient = MyChromeClient()

        mWebView.setListener(this, this)
        mWebView.setMixedContentAllowed(false)

        setSettings()

        if (savedInstanceState == null) {
            mWebView.post {
                kotlin.run { mWebView.loadUrl(url) }
            }
        }

        mWebView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.action == MotionEvent.ACTION_UP &&
                mWebView.canGoBack()
            ) {
                mWebView.goBack()
                return@OnKeyListener true
            }
            false
        })

    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        mWebView.onResume()
        // ...
    }

    @SuppressLint("NewApi")
    override fun onPause() {
        mWebView.onPause()
        // ...
        super.onPause()
    }

    override fun onDestroy() {
        mWebView.onDestroy()
        // ...
        super.onDestroy()
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
//        if (requestCode == REQUEST_CODE) {
//            filePathCallback!!.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(
//                resultCode,
//                intent))
//            filePathCallback = null
//        }
        mWebView.onActivityResult(requestCode, resultCode, intent)
    }

    override fun onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return
        }
        finishAndRemoveTask()
        System.exit(0)
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {}

    override fun onPageFinished(url: String?) {}

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {}

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?,
    ) {
    }

    override fun onExternalPageRequest(url: String?) {}

    @SuppressLint("SetJavaScriptEnabled")
    private fun setSettings() {
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.allowFileAccess = true
        webSettings.domStorageEnabled = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.useWideViewPort = true
        webSettings.setSupportZoom(true)
        webSettings.setAppCacheEnabled(true)
        webSettings.userAgentString = webSettings.userAgentString.replace("; wv", "")
    }

    var filePathCallback: ValueCallback<Array<Uri>>? = null
    private val REQUEST_CODE = 100

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mWebView.saveState(outState)
    }


    inner class MyChromeClient : WebChromeClient() {

        override fun onShowFileChooser(
            view: WebView,
            filePath: ValueCallback<Array<Uri>>,
            fileChooserParams: FileChooserParams,
        ): Boolean {
            filePathCallback = filePath
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
            startActivityForResult(intent, REQUEST_CODE)
            return true
        }


        private var mCustomView: View? = null
        private var mCustomViewCallback: CustomViewCallback? = null
        private var mOriginalOrientation = 0
        private var mOriginalSystemUiVisibility = 0

        override fun getDefaultVideoPoster(): Bitmap? {
            return if (mCustomView == null) {
                null
            } else BitmapFactory.decodeResource(this@WebActivity.applicationContext.resources,
                2130837573)
        }

        override fun onHideCustomView() {
            (this@WebActivity.window.decorView as FrameLayout).removeView(mCustomView)
            mCustomView = null
            this@WebActivity.window.decorView.systemUiVisibility = mOriginalSystemUiVisibility
            this@WebActivity.requestedOrientation = mOriginalOrientation
            mCustomViewCallback!!.onCustomViewHidden()
            mCustomViewCallback = null
        }

        override fun onShowCustomView(
            paramView: View?,
            paramCustomViewCallback: CustomViewCallback?,
        ) {
            if (mCustomView != null) {
                onHideCustomView()
                return
            }
            mCustomView = paramView
            mOriginalSystemUiVisibility = this@WebActivity.window.decorView.systemUiVisibility
            mOriginalOrientation = this@WebActivity.requestedOrientation
            mCustomViewCallback = paramCustomViewCallback
            (this@WebActivity.window.decorView as FrameLayout).addView(
                mCustomView,
                FrameLayout.LayoutParams(-1, -1)
            )
            this@WebActivity.window.decorView.systemUiVisibility =
                3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}