package com.acquaintsoft.notification

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.acquaintsoft.notification.services.BackgroundService
import com.acquaintsoft.notification.utils.Constants
import com.acquaintsoft.notification.utils.PrefUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Task 1:check app is on production mode or debug mode(Test Mode)
        if (BuildConfig.DEBUG) {
            tvAppMode.text = getString(R.string.qa)
        } else {
            tvAppMode.text = getString(R.string.production)
        }


        //Task 2 Check timer is started or not if timer is not started then start background service with 5 min timer
        if (!PrefUtils.getBoolean(this, Constants.PREF_ON_STARTED, false)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                startForegroundService(Intent(this@MainActivity, BackgroundService::class.java))
            else startService(Intent(this@MainActivity, BackgroundService::class.java))
        }

    }


}
