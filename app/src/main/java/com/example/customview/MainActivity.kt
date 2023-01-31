package com.example.customview

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomButton.setListener {
            if (it == BottomButtonActions.POSITIVE) {

                binding.bottomButton.setPositiveButtonText("Update Apply")
                Toast.makeText(this, R.string.positive_btn, Toast.LENGTH_SHORT).show()

            } else if (it == BottomButtonActions.NEGATIVE) {

                binding.bottomButton.setNegativeButtonText("Update Cancel")
                Toast.makeText(this, R.string.negative_btn, Toast.LENGTH_SHORT).show()
            }
        }
    }
}