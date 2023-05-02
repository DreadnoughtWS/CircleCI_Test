package com.academy.alfagiftmini.presentation.homepage.components.fragment.banner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentBannerBerandaBinding
import com.academy.alfagiftmini.databinding.FragmentDetailofficialNamaProductBinding
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel

class FragmentBannerBeranda : Fragment() {
    private lateinit var binding: FragmentBannerBerandaBinding
    private lateinit var viewModel: BannerListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBannerBerandaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}