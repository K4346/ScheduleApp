package com.example.scheduleapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.scheduleapp.databinding.ActivityMainBinding
import com.example.scheduleapp.ui.firstActivity.FirstActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }
}