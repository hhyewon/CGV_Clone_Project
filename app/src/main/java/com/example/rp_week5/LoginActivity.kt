package com.example.rp_week5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week5.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: LoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kakaoLogin.setOnClickListener {

        }
    }
}