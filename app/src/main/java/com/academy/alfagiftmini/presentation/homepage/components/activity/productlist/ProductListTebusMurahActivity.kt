package com.academy.alfagiftmini.presentation.homepage.components.activity.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductListTebusMurahBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import javax.inject.Inject

class ProductListTebusMurahActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListTebusMurahBinding

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productListTebusMurahActivityInject(this)
        binding = ActivityProductListTebusMurahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}