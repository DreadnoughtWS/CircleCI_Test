package com.academy.alfagiftmini.presentation.homepage.components.activity.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityAllBannerListBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import javax.inject.Inject

class AllBannerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllBannerListBinding

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
    }
}