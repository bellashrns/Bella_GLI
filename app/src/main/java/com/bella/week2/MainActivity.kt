package com.bella.week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bella.week2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginBtn = binding.loginBtn
        val productBtn = binding.productBtn

        loginBtn.setOnClickListener {
            activityIntent(LoginActivity::class.java)
        }

        productBtn.setOnClickListener {
            activityIntent(ProductActivity::class.java)
        }
    }

    private fun activityIntent(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}