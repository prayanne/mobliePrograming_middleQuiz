package com.example.mini_test_moblie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
        val menuItem = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return true
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val returnValue = data?.getStringExtra("result")
            Toast.makeText(this, "결과: $returnValue", Toast.LENGTH_SHORT).show()
            binding.calcResult.text = returnValue
        }
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
                val popup = PopupMenu(this, toolbar, Gravity.END)

                popup.menuInflater.inflate(R.menu.popup_more, popup.menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.action_calc1 -> {
                            val intent = Intent(this, CalcActivity::class.java)
                            resultLauncher.launch(intent)
                            true
                        }

                        R.id.action_calc2 -> {
                            val intent = Intent(this, Calc2Activity::class.java)
                            resultLauncher.launch(intent)
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