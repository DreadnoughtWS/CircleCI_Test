package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel
import kotlinx.coroutines.CoroutineScope
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
        return useCase.getProductGratisProductOrder(type = type,order = order,sort = sort,scope = viewModelScope)
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

    suspend fun getProductSearchProductOrder(name:String, order:String, sort:String,type: String):Flow<PagingData<ProductListPromotionProductDomainModel>>{
        return useCase.getProductSearchProductOrder(viewModelScope,name,order,sort,type)
    }

    suspend fun getProductSearchProduct(name:String,type:String):Flow<PagingData<ProductListPromotionProductDomainModel>>{
        return useCase.getProductSearchProduct(viewModelScope,name,type)
    }

    suspend fun getBannerProduct(bannerId:Int,order: String,sort: String,type:String):Flow<PagingData<ProductListPromotionProductDomainModel>>{
        return useCase.getBannerProduct(scope = viewModelScope,bannerId = bannerId,order = order,sort = sort,type = type)
    }

    suspend fun getProductByCategory(scope: CoroutineScope, subCategory: String, category: String, sort: String, order: String, type: String): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return useCase.getProductByCategory(scope, subCategory, category, sort, order, type)
    }

    private val _itemCount = MutableLiveData<Int>()
    val itemCount: MutableLiveData<Int> = _itemCount

    fun setItemAmount(itemCount: Int) {
        _itemCount.value = itemCount
    }
}