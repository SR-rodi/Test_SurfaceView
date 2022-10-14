package com.example.surface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.surface.databinding.ActivityMainBinding
import com.example.surface.gameview.GameView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
    }
}