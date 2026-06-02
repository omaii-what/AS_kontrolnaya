package com.example.as_kontrolnaya

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.as_kontrolnaya.databinding.ActivityMainBinding
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import kotlin.properties.Delegates.notNull

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var g by notNull<Boolean>()
    var total = false
    var each = false
    var pplAmount = false
    var tips = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null){
            g = false
        }else{
            g = savedInstanceState.getBoolean(KEY_IS_VISIBLE)
        }

        binding.roundUp.setOnClickListener {
            if(binding.roundUp.isChecked){g = false
                render()}
        }
        binding.roundDown.setOnClickListener {
            if (binding.roundDown.isChecked) {g = true
                render()}
        }
        binding.roundClosest.setOnClickListener {
            if (binding.roundClosest.isChecked) {g = true
                render()}
        }
    }

    private fun render() = with(binding){
        roundPick.visibility = if (g) View.VISIBLE else View.INVISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_VISIBLE,g)
    }

    companion object{
        @JvmStatic private val KEY_IS_VISIBLE = "IS_VISIBLE"
    }
}