package com.example.mini_test_moblie

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class newActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNewBinding.inflate(layout_inflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        
    }
}