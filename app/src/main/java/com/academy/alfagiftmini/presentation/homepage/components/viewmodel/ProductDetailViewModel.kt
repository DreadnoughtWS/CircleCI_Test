package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.productdetail.ProductDetailUseCase
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.academy.alfagiftmini.domain.productdetail.model.ProductPromosi103DomainModel
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(private val useCase:ProductDetailUseCase):ViewModel() {

    private val _productDetailDataLive= MutableLiveData<List<ProductDetailDomainModel>>()
    val  productDetailDataLive : LiveData<List<ProductDetailDomainModel>> =   _productDetailDataLive

    suspend fun getProductDetailLive(productId:Long){
        useCase.getProductDetail(productId).collectLatest {
            _productDetailDataLive.postValue(it)
        }
    }

    private val _productDetailData= MutableStateFlow(listOf<ProductDetailDomainModel>())
    val  productDetailData : StateFlow<List<ProductDetailDomainModel>> =   _productDetailData

    suspend fun getProductDetail(productId:Long){
        useCase.getProductDetail(productId).collectLatest {
            _productDetailData.value = it
        }
    }

    private val _productGratisData= MutableStateFlow(listOf<ProductPromosi103DomainModel>())
    val  productGratisData : StateFlow<List<ProductPromosi103DomainModel>> =   _productGratisData
    suspend fun getProductPromoGratis(productId:Long){
        useCase.getProductGratis(productId).collectLatest {
            _productGratisData.value = it
        }
    }

}
