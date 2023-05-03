package com.academy.alfagiftmini.presentation.homepage.components.fragment.banner

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.databinding.FragmentBannerBerandaBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.BannerPromoItemListActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.banner.BannerBerandaSliderAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentBannerBeranda : Fragment() {
    private lateinit var binding: FragmentBannerBerandaBinding
    private lateinit var adapter: BannerBerandaSliderAdapter

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: BannerListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBannerBerandaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (PresentationUtils.isNetworkAvailable(requireContext())){
//            yesConnection()
            getLiveData()
        }
//        else{
//            noConnection()
//        }

        binding.tvLihatSemuaBanner.setOnClickListener {
            val intent = Intent(context, BannerPromoItemListActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun getLiveData() {
        lifecycleScope.launch {
            viewModel.getAllBannerList()
            viewModel.bannerListData.collectLatest {
                adapter = BannerBerandaSliderAdapter(it.bannerList,requireContext())
                binding.svSliderBanner.setSliderAdapter(adapter)
                binding.svSliderBanner.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
                binding.svSliderBanner.scrollTimeInSec = 2
                binding.svSliderBanner.startAutoCycle()
            }
        }
    }

//    fun noConnection(){
//        binding.apply {
//            tvNoConnect.visibility = View.VISIBLE
//            tvLihatSemuaBanner
//
//        }
//    }

//    fun yesConnection(){
//        binding.apply {
//            tvNoConnect.visibility = View.INVISIBLE
//        }
//    }




}