package com.academy.alfagiftmini.presentation.homepage.components.activity.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityProductDetailBinding
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailResponseModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productdetail.ProductDetailSliderAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var adapter: ProductDetailSliderAdapter

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductDetailViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productDetailActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setToolbar()
        if (PresentationUtils.isNetworkAvailable(this)){
            getProductId()
        }

    }

    private fun setToolbar(){
        binding.bannerItemListToolbar.ivDetailBackToolbar.setOnClickListener {
            finish()
        }
    }

    private fun getProductData(productId: Int){
        lifecycleScope.launch {
            viewModel.getProductDetail(productId)
            viewModel.productDetailData.collectLatest {
                adapter = ProductDetailSliderAdapter(it.productList[0].productImages[0].url,this@ProductDetailActivity)
                setDataPresentation(it)
            }
        }
    }

    private fun getProductId() {
        val productIdIntent = intent.getIntExtra(PresentationUtils.PRODUCT_ID,0)
        if (productIdIntent != 0) {
            getProductData(productIdIntent)
        }
    }

    private fun setDataPresentation(it: ProductDetailResponseModel){

    }
}