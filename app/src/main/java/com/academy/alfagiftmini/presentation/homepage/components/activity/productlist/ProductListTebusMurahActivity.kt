package com.academy.alfagiftmini.presentation.homepage.components.activity.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductListTebusMurahBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListTebusMurahAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListTebusMurahProductAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListTebusMurahActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListTebusMurahBinding
    private lateinit var tebusMurahAdapter: ProductListTebusMurahAdapter

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
        setAdapter()
        getData()
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getProductListTebusMurah().collect {
                tebusMurahAdapter.updateDaata(it)
            }
        }
    }

    private fun setAdapter() {
        tebusMurahAdapter = ProductListTebusMurahAdapter()
        binding.rvProductListTebusMurah.layoutManager = LinearLayoutManager(this)
        binding.rvProductListTebusMurah.adapter = tebusMurahAdapter
    }
}