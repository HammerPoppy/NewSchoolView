package com.taria.newschoolview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.webkit.WebView

class TableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        var page = intent.getStringExtra(EXTRA_MESSAGE)
        Log.i("debug", "got page: \n$page")

        val wv = findViewById<WebView>(R.id.wb_main)

        if (page != null) {
            Log.i("debug", "loading page to webview")

            wv.loadData(page, "text/html; charset=UTF-8;", null)
        } else {
            Log.i("debug", "page is null")
        }
    }

}