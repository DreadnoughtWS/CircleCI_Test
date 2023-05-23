package com.academy.alfagiftmini.presentation.homepage.components.activity.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivitySyaratKetentuanBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.PresentationUtils.fromHtml

class SyaratKetentuanActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySyaratKetentuanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySyaratKetentuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
        receiveIntent()
    }

    private fun setupLayout(syarat: String) {
        binding.apply {
            tvSyaratDanKetentuan.text = syarat.fromHtml()
        }
    }

    private fun setupButtons() {
        binding.apply {
            ibBackButton.setOnClickListener {
                finish()
            }
            btTutupGratisButton.setOnClickListener {
                finish()
            }
        }
    }

    private fun receiveIntent() {
        val syarat = intent.getStringExtra(PresentationUtils.BANNER_RULE)
       if (!syarat.isNullOrEmpty()){
           setupLayout(syarat)
       }
    }
}