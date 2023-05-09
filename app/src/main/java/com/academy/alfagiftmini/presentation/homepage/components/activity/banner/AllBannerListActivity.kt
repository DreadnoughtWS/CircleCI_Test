package com.academy.alfagiftmini.presentation.homepage.components.activity.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityAllBannerListBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.banner.BannerListAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllBannerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllBannerListBinding
    private lateinit var adapter: BannerListAdapter

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: BannerListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.bannerListActivityInject(this)
        binding = ActivityAllBannerListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setToolbar()
        if (PresentationUtils.isNetworkAvailable(this)){
            getLiveData()
        }

    }

    private fun setToolbar() {
        binding.allBannerListToolbar.tvPromoToolbarTitle.text = getString(R.string.banner_list_title)
        binding.allBannerListToolbar.btnBannerBack.setOnClickListener {
            finish()
        }
    }

    private fun getLiveData() {
        lifecycleScope.launch {
            viewModel.getAllBannerList()
            viewModel.bannerListData.collectLatest {
                binding.rvBannerList.layoutManager = LinearLayoutManager(this@AllBannerListActivity)
                adapter = BannerListAdapter(it)
                binding.rvBannerList.adapter = adapter

            }
        }
    }
}