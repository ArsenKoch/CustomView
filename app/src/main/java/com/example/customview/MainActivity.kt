package com.example.customview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(binding.root) }

        binding.bottomButton.setListener {
            if (it == BottomButtonActions.POSITIVE) {
                Toast.makeText(this, "You touch positive button!", Toast.LENGTH_SHORT).show()
            } else if (it == BottomButtonActions.NEGATIVE) {
                Toast.makeText(this, "You touch negative button!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}