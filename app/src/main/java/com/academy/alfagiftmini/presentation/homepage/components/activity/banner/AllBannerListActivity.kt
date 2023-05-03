package com.academy.alfagiftmini.presentation.homepage.components.activity.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityAllBannerListBinding
import com.academy.alfagiftmini.databinding.ActivityBannerPromoItemListBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.banner.BannerBerandaSliderAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.banner.BannerListAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import com.smarteist.autoimageslider.SliderView
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

        if (PresentationUtils.isNetworkAvailable(this)){
            getLiveData()
        }

        /*binding.btnBannerBack.setOnClickListener {
            finish()
        }*/

    }

    private fun getLiveData() {
        lifecycleScope.launch {
            viewModel.getAllBannerList()
            viewModel.bannerListData.collectLatest {
                adapter = BannerListAdapter(it.bannerList,this@AllBannerListActivity)
                binding.rvBannerList.adapter = adapter
                binding.rvBannerList.layoutManager = LinearLayoutManager(this@AllBannerListActivity)
            }
        }
    }
}