package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.productdetail.ProductDetailUseCase
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailResponseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(private val useCase:ProductDetailUseCase):ViewModel() {

    private val _productDetailData= MutableStateFlow(ProductDetailResponseModel(listOf()))
    val  productDetailData : StateFlow<ProductDetailResponseModel> =   _productDetailData

    suspend fun getProductDetail(productId:Int){
        useCase.getProductDetail(productId).collectLatest {
            _productDetailData.value = it
        }
    }

}