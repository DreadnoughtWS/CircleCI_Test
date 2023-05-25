package com.academy.alfagiftmini.presentation.homepage.components.fragment.banner

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.databinding.FragmentBannerBerandaBinding
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.AllBannerListActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.banner.BannerBerandaSliderAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import com.academy.alfagiftmini.presentation.homepage.fragment.FragmentBeranda
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentBannerBeranda() : Fragment() {
    private lateinit var binding: FragmentBannerBerandaBinding
    private lateinit var adapter: BannerBerandaSliderAdapter
    private lateinit var viewModel: BannerListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBannerBerandaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as MainActivity).getBannerListsViewModel()
        if (PresentationUtils.isNetworkAvailable(requireContext())){
//            yesConnection()
            getLiveData()
        }
//        else{
//            noConnection()
//        }

        binding.tvLihatSemuaBanner.setOnClickListener {
            val intent = Intent(context, AllBannerListActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun getLiveData() {
        viewModel.getAllBannerList()
        lifecycleScope.launch {
            viewModel.bannerListData.collectLatest {
                setupSlider(it)
            }

        }
    }

    private fun setupSlider(it: List<BannerDomainModel>){
        adapter = BannerBerandaSliderAdapter(it,requireContext())
        binding.svSliderBanner.setSliderAdapter(adapter)
        binding.svSliderBanner.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.svSliderBanner.startAutoCycle()
    }


}