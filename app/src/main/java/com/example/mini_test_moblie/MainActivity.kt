package com.example.mini_test_moblie

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import com.example.mini_test_moblie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "검색 클릭됨", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_more -> {
                // 툴바에서 팝업 메뉴 띄우기
                val toolbar = binding.toolbar
                val popup = PopupMenu(this, toolbar)
                popup.menuInflater.inflate(R.menu.popup_more, popup.menu)

                popup.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.action_calc1 -> {
                            startActivity(Intent(this, CalcActivity::class.java))
                            true
                        }

                        R.id.action_calc2 -> {
                            startActivity(Intent(this, Calc2Activity::class.java))
                            true
                        }

                        R.id.action_newActivity -> {
                            startActivity(Intent(this, newActivity::class.java))
                            true
                        }

                        else -> false
                    }
                }

                popup.show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}