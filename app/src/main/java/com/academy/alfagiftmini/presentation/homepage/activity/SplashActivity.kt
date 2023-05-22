package com.academy.alfagiftmini.presentation.homepage.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.presentation.authentication.activity.LoginActivity
import java.util.Timer
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(3000L) {
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
        }
    }
}