package com.jinyeob.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinyeob.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip(){
        val cost = binding.costOfService.text.toString().toDoubleOrNull()

        if(cost == null){
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.2
            R.id.option_eightteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.15
        }
        var tip = cost * tipPercentage
        if(binding.RoundUpTipSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}