package com.example.a2021158008_com

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import com.example.a2021158008_com.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

    }
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            val resultValue = data?.getStringExtra("result")
            binding.resultData.setText(resultValue)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        var menuItem = menu?.findItem(R.id.search_button)
        var searchView = menuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.more_button -> {
                val toolbar = binding.toolbar
                val popup = PopupMenu(this, toolbar, Gravity.END)
                popup.menuInflater.inflate(R.menu.menu_popup, popup.menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.goCalc1 -> {
                            var intent = Intent(this@MainActivity, CalcActivity::class.java)
                            intent.putExtra("calcType", false)
                            resultLauncher.launch(intent)
                            true
                        }

                        R.id.goCalc2 -> {
                            var intent = Intent(this@MainActivity, CalcActivity::class.java)
                            intent.putExtra("calcType", true)
                            resultLauncher.launch(intent)
                            true
                        }

                        R.id.goNew -> {
                            startActivity(Intent(this@MainActivity, NewActivity::class.java))
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