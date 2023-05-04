package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.data.repository.network.officialstore.OfficialStoreListPagingSource
import com.academy.alfagiftmini.databinding.ActivityOfficialStoreSearchBinding
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.AllOfficialStorePagingAdapter

class OfficialStoreSearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOfficialStoreSearchBinding
    private lateinit var adapter: AllOfficialStorePagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.officialStoreSearchActivityInject(this)
        binding = ActivityOfficialStoreSearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setHideToolbar()
        setToolbar()
    }

    private fun setToolbar() {
        binding.officialStoreSeachToolbar.btnBannerBack.setOnClickListener {
            finish()
        }
    }

    private fun setHideToolbar() {
        supportActionBar?.hide()
    }
}