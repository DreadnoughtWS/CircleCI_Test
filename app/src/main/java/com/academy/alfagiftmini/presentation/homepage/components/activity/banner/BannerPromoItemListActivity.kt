package com.academy.alfagiftmini.presentation.homepage.components.activity.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityBannerPromoItemListBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.banner.BannerListAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import javax.inject.Inject

class BannerPromoItemListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBannerPromoItemListBinding

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: BannerListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBannerPromoItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}