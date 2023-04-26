package com.academy.alfagiftmini.presentation.homepage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListViewModel @Inject constructor(private val useCase: ProductListDomainUseCase) :
    ViewModel() {

    suspend fun getAllProductList(
        type:Int
    ): Flow<PagingData<ProductListDomainItemModel>> {
        return useCase.getAllProduct(viewModelScope,type)
    }

    suspend fun getProductOrder(
        type:Int,
        order:String,
        sort:String
    ): Flow<PagingData<ProductListDomainItemModel>> {
        return useCase.getProductOrder(viewModelScope,type,order,sort)
    }

    suspend fun getProductGratisProduct(
        type:Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return useCase.getProductGratisProduct(viewModelScope,type)
    }

    suspend fun getProductGratisProductOrder(
        type:Int,
        order:String,
        sort:String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return useCase.getProductGratisProductOrder(viewModelScope,type,order,sort)
    }

}