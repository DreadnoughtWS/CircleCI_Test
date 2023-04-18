package com.academy.alfagiftmini.presentation.homepage.activity

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityOfficialStoreBinding

class OfficialStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOfficialStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.officialStoreActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityOfficialStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}