package com.example.mini_test_moblie

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mini_test_moblie.databinding.ActivityCalcBinding

class CalcActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalcBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var calcBool: Boolean = false
        var tempCalc: String = ""
        var calcStr: String = ""

        fun pushString(s:String, t:String){
            if (t == "num"){ binding.numBlank.setText(binding.numBlank.text.toString() + s) }
            else if(t == "calc"){ binding.numBlank.setText(binding.numBlank.text.toString() + " " + s) }
            else if(t == "result") { binding.numBlank.setText(s) }
        }

        fun calculator(i: String, n: String, c:String): Int{
            when (c) {
                "+" -> return i.toInt() + n.toInt()
                "-" -> return i.toInt() - n.toInt()
                "x" -> return i.toInt() * n.toInt()
                "/" -> return i.toInt() / n.toInt()
            }
            return -1
        }

        fun addInt(i:String){
            calcBool = true
            tempCalc += i
            pushString(i, "num")
        }

        fun addCalcStr(s:String){
            if(calcBool && tempCalc != ""){
                calcBool = false

                if (calcStr == "") calcStr += s
                else calcStr += "," + s

                tempCalc += ","

                pushString(s, "calc")

                // disable calcStr function
            }
        }

        fun pushResult(): Int{
            var result: Int = 0
            if (calcBool && tempCalc != ""){
                var numList = tempCalc.split(",")
                var calcList = calcStr.split(",")

                for ((i, num) in numList.withIndex()) {
                    if (i == numList.lastIndex) break
                    if ( i == 0){
                        when (calcList[i]) {
                            "+" -> result = calculator(num[0].toString(), num[1].toString(), "+")
                            "-" -> result = calculator(num[0].toString(), num[1].toString(), "-")
                            "x" -> result = calculator(num[0].toString(), num[1].toString(), "x")
                            "/" -> result = calculator(num[0].toString(), num[1].toString(), "/")
                        }
                    } else {
                        when (calcList[i]) {
                            "+" -> result = calculator(result.toString(), num[i + 1].toString(), "+")
                            "-" -> result = calculator(result.toString(), num[i + 1].toString(), "-")
                            "x" -> result = calculator(result.toString(), num[i + 1].toString(), "*")
                            "/" -> result = calculator(result.toString(), num[i + 1].toString(), "/")
                        }
                    }

                }
            }
            pushString(result.toString(), "result")
            return result
        }
        binding.calc0.setOnClickListener{ addInt("0") }
        binding.calc1.setOnClickListener{ addInt("1") }
        binding.calc2.setOnClickListener{ addInt("2") }
        binding.calc3.setOnClickListener{ addInt("3") }
        binding.calc4.setOnClickListener{ addInt("4") }
        binding.calc5.setOnClickListener{ addInt("5") }
        binding.calc6.setOnClickListener{ addInt("6") }
        binding.calc7.setOnClickListener{ addInt("7") }
        binding.calc8.setOnClickListener{ addInt("8") }
        binding.calc9.setOnClickListener{ addInt("9") }


        binding.calcP.setOnClickListener{ addCalcStr("+") }
        binding.calcM.setOnClickListener{ addCalcStr("-") }
        binding.calcX.setOnClickListener{ addCalcStr("x") }
        binding.calcD.setOnClickListener{ addCalcStr("/") }

        binding.calcE.setOnClickListener{ pushResult() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}