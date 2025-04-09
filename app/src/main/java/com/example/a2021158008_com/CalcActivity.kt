package com.example.a2021158008_com

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2021158008_com.databinding.ActivityCalcBinding

class CalcActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalcBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var priStr = ""
        var latStr = ""
        var oper = ""
        var returnMessage = ""
        var priSta = false
        var operSta = false

        binding.toolbar.title = "계산기1"

        fun calcType(b: Boolean?){
            if(b == true){
                Log.d("calcChk", "$b")
                binding.toolbar.title = "계산기2"

                val lpP = binding.calcP.layoutParams as GridLayout.LayoutParams
                lpP.columnSpec = GridLayout.spec(2, 1, 1f)
                binding.calcP.layoutParams = lpP

                val lpM = binding.calcM.layoutParams as GridLayout.LayoutParams
                lpM.columnSpec = GridLayout.spec(1, 1,1f)
                lpM.rowSpec = GridLayout.spec(3, 1, 1f)
                binding.calcM.layoutParams = lpM

                val lpE = binding.calcE.layoutParams as GridLayout.LayoutParams
                lpE.columnSpec = GridLayout.spec(3, 1, 1f)
                lpE.rowSpec = GridLayout.spec(2,2, 2f)
                binding.calcE.layoutParams = lpE
            }
        }
        val calType = intent.getBooleanExtra("calcType", true)
        // val calType = intent.hasExtra("calcType") //도 가능하다. 위의 defaultValue는 기본값이 없을 경우 에러가 발생할 수 있기 때문임.
        calcType(calType)

        fun enable(){
//            Log.d("calcChk", "$priSta $operSta")

            if (!priSta && operSta){
                binding.calcP.isEnabled = true
                binding.calcM.isEnabled = true
                binding.calcX.isEnabled = true
                binding.calcD.isEnabled = true

                binding.calcE.isEnabled = false
            }
            else if (!priSta) {
                binding.calcP.isEnabled = false
                binding.calcM.isEnabled = false
                binding.calcX.isEnabled = false
                binding.calcD.isEnabled = false

                binding.calcE.isEnabled = true
            }
            else if (priSta && operSta) {
                binding.calcP.isEnabled = false
                binding.calcM.isEnabled = false
                binding.calcX.isEnabled = false
                binding.calcD.isEnabled = false

                binding.calcE.isEnabled = true
            }
        }

        fun addVal(i: String){
            if (!priSta) {
                operSta = true
                priStr += i
                binding.resultVal.setText(priStr)
            }
            else if(priSta && operSta) {
                latStr += i
                binding.resultVal.setText(priStr + " " + oper + " " + latStr)
            }
            enable()
        }
        fun calcResult():Int{
            when(oper){
                "+" -> return priStr.toInt() + latStr.toInt()
                "-" -> return priStr.toInt() - latStr.toInt()
                "x" -> return priStr.toInt() * latStr.toInt()
                "/" -> return priStr.toInt() / latStr.toInt()
            }
            return -1
        }
        fun operation(s: String){
            priSta = true
            oper += s
            binding.resultVal.setText(priStr + " " + oper)
            enable()
        }
        fun equal(){
            if(priSta && operSta){
                returnMessage = "수식: " + priStr + " " + oper + " " + latStr + " = " + calcResult().toString()
                Toast.makeText(this@CalcActivity, returnMessage, Toast.LENGTH_SHORT).show()
            }
        }


        binding.calc0.setOnClickListener{addVal("0")}
        binding.calc1.setOnClickListener{addVal("1")}
        binding.calc2.setOnClickListener{addVal("2")}
        binding.calc3.setOnClickListener{addVal("3")}
        binding.calc4.setOnClickListener{addVal("4")}
        binding.calc5.setOnClickListener{addVal("5")}
        binding.calc6.setOnClickListener{addVal("6")}
        binding.calc7.setOnClickListener{addVal("7")}
        binding.calc8.setOnClickListener{addVal("8")}
        binding.calc9.setOnClickListener{addVal("9")}

        binding.calcP.setOnClickListener{operation("+")}
        binding.calcM.setOnClickListener{operation("-")}
        binding.calcX.setOnClickListener{operation("x")}
        binding.calcD.setOnClickListener{operation("/")}
        binding.calcE.setOnClickListener{
            equal()
            binding.resultVal.setText(calcResult().toString())
            val returnIntent = Intent().apply{
                putExtra("result", returnMessage)
            }
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}