package com.academy.alfagiftmini.presentation.homepage.components.activity.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivitySyaratKetentuanBinding

class SyaratKetentuanActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySyaratKetentuanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySyaratKetentuanBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}