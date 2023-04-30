package com.academy.alfagiftmini.presentation.homepage.components.activity.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductListPaketBinding
import com.academy.alfagiftmini.databinding.ActivityProductListSearchProdukBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import javax.inject.Inject

class ProductListSearchProduk : AppCompatActivity() {
    private lateinit var binding: ActivityProductListSearchProdukBinding

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productListSearchProductActivityInject(this)
        binding = ActivityProductListSearchProdukBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setHideToolbar()
    }

    private fun setHideToolbar() {
        supportActionBar?.hide()
    }
}