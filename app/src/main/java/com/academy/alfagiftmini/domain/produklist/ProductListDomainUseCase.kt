package com.academy.alfagiftmini.domain.produklist

import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ProductListDomainUseCase {

    suspend fun getAllProduct(scope: CoroutineScope,type:Int): Flow<PagingData<ProductListDomainItemModel>>
    suspend fun getProductOrder(scope: CoroutineScope,type:Int,order:String,sort:String): Flow<PagingData<ProductListDomainItemModel>>
    suspend fun getProductGratisProduct(scope:CoroutineScope, type:Int):Flow<PagingData<ProductListPromotionProductDomainModel>>

    suspend fun getProductGratisProductOrder(scope: CoroutineScope,type:Int,order:String,sort:String): Flow<PagingData<ProductListPromotionProductDomainModel>>

}