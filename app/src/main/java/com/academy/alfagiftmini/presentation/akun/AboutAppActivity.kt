package com.academy.alfagiftmini.presentation.akun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityAboutAppBinding

class AboutAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.tentangAplikasiActivityInject(this)
        binding = ActivityAboutAppBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setTextTentangAlfagift()
        setToolbar()
    }

    private fun setToolbar() {
        binding.tvPromoToolbarTitle.text = getString(R.string.tentang_alfagift)
        binding.btnBannerBack.setOnClickListener { finish() }
    }

    private fun setTextTentangAlfagift() {
        binding.tvTentangAlfagift.text = getString(R.string.penjelasan_tentang_aplikasi_ini)
    }
}