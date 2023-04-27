package com.academy.alfagiftmini.presentation.homepage.components.activity.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityAllBannerListBinding

class AllBannerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllBannerListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBannerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}