package com.example.catalogue.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.catalogue.R
import com.example.catalogue.component.main.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            },
            3500L
        )
    }
}