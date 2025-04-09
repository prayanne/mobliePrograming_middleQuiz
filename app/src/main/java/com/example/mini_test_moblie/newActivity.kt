package com.example.mini_test_moblie

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mini_test_moblie.databinding.ActivityNewBinding

class newActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
    setSupportActionBar(binding.toolbar)
        
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // 현재 액티비티 종료
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}