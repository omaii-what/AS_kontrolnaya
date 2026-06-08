package com.example.as_kontrolnaya

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.as_kontrolnaya.databinding.ActivityMainBinding
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipsPercent.setText("15")
        binding.peopleAmount.setText("1")

        binding.countButton.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() = with(binding) {
        try {
            val bill = totalAmount.text.toString().toDouble()
            val tipsPercent = if (tipsPercent.text.toString().isEmpty()) 15 else tipsPercent.text.toString().toInt()
            val people = if (peopleAmount.text.toString().isEmpty()) 1 else peopleAmount.text.toString().toInt()

            if (bill <= 0) {
                Toast.makeText(this@MainActivity, "ОШИБКА!! Укажите корректную сумму", Toast.LENGTH_SHORT).show()
                return
            }

            if (bill <= 0 || tipsPercent < 5 || tipsPercent > 30) {
                Toast.makeText(this@MainActivity, "ОШИБКА!! Укажите корректные чаевые", Toast.LENGTH_SHORT).show()
                return
            }

            if (people < 1 || people > 20) {
                Toast.makeText(this@MainActivity, "ОШИБКА!! Укажите корректное число людей", Toast.LENGTH_SHORT).show()
                return
            }

            var tips = bill * (tipsPercent / 100.0)

            if (roundUp.isChecked) {
                if (tips > tips.toInt()) {
                    tips = (tips.toInt() + 1).toDouble()
                }
            }
            if (roundDown.isChecked) {
                tips = tips.toInt().toDouble()
            }
            if (roundClosest.isChecked) {
                val drob = tips - tips.toInt()
                if (drob >= 0.5) {
                    tips = (tips.toInt() + 1).toDouble()
                } else {
                    tips = tips.toInt().toDouble()
                }
            }

            val total = bill + tips
            val each = total / people

            billText.text = "Сумма чаевых: %.2f".format(tips)
            sumText.text = "Общая сумма: %.2f".format(total)
            eachText.text = "На каждого: %.2f".format(each)

            hiddenSum.visibility = android.view.View.VISIBLE

        } catch(e: Exception) {
            Toast.makeText(this@MainActivity, "Ошибка", Toast.LENGTH_SHORT).show()
        }
    }
}