package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListViewModel @Inject constructor(private val useCase: ProductListDomainUseCase) :
    ViewModel() {

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

    suspend fun getDetailOfficialStorePromosiProduct(
        officialStoreId:Int
    ):Flow<PagingData<ProductListPromotionProductDomainModel>>{
        return useCase.getDetailOfficialStoreProductPromosi(viewModelScope,officialStoreId)
    }

    suspend fun getDetailOffiialStoreOrder(
        order:String,
        sort:String,
        officialStoreId: Int
    ):Flow<PagingData<ProductListPromotionProductDomainModel>>{
        return useCase.getDetailOfficialStoreOrder(viewModelScope,order,sort,officialStoreId)
    }

    suspend fun getProductListTebusMurah():Flow<List<ProductListTebusMurahDomainModel>>{
        return useCase.getProductTebusMurah()
    }

    suspend fun getPreviewProductName(name:String):Flow<List<ProductListDomainItemModel>>{
        return useCase.getProductByName(name)
    }

    suspend fun getProductSearchProductOrder(name:String, order:String, sort:String):Flow<PagingData<ProductListPromotionProductDomainModel>>{
        return useCase.getProductSearchProductOrder(viewModelScope,name,order,sort)
    }

    suspend fun getProductSearchProduct(name:String):Flow<PagingData<ProductListPromotionProductDomainModel>>{
        return useCase.getProductSearchProduct(viewModelScope,name)
    }

}