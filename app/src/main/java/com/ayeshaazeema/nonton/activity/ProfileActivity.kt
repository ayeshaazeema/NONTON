package com.ayeshaazeema.nonton.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayeshaazeema.nonton.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()
    }
}