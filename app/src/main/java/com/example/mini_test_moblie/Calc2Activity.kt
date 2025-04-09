package com.example.mini_test_moblie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.mini_test_moblie.databinding.ActivityCalc2Binding


class Calc2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCalc2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalc2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var endState: Boolean = false
        var calcBool: Boolean = false
        var tempCalc: String = ""
        var calcStr: String = ""

        fun pushString(s:String, t:String){
            Log.d("calc", endState.toString())
            if (endState) { endState = false; binding.numBlank.setText("")}
            if (t == "num"){
                binding.numBlank.setText(binding.numBlank.text.toString() + s) }
            else if(t == "calc"){ binding.numBlank.setText(binding.numBlank.text.toString() + " " + s + " ") }
            else if(t == "result") { binding.numBlank.setText(s) }
        }

        fun calculator(i: String, n: String, c:String): Int{
            Log.d("calc", i + ", " + n + ", " + i.toInt() + n.toInt())
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
                            "+" -> result = calculator(num, numList[i + 1], "+")
                            "-" -> result = calculator(num, numList[i + 1], "-")
                            "x" -> result = calculator(num, numList[i + 1], "x")
                            "/" -> result = calculator(num, numList[i + 1], "/")
                        }
                    } else {
                        when (calcList[i]) {
                            "+" -> result = calculator(result.toString(), numList[i + 1], "+")
                            "-" -> result = calculator(result.toString(), numList[i + 1], "-")
                            "x" -> result = calculator(result.toString(), numList[i + 1], "*")
                            "/" -> result = calculator(result.toString(), numList[i + 1], "/")
                        }
                    }
                }
            }
            pushString(result.toString(), "result")

            endState = true
            calcBool = false
            tempCalc = ""
            calcStr = ""

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

        // 결과 반환
        binding.calcE.setOnClickListener{
            val result: Int
            result = pushResult()
            val returnIntent = Intent().apply {
                putExtra("result", result.toString())
            }
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}