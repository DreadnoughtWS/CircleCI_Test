package com.academy.alfagiftmini.domain.produklist

import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ProductListDomainRepository {

    suspend fun getProductGratisProduct(scope:CoroutineScope, type:Int):Flow<PagingData<ProductListPromotionProductDomainModel>>
    suspend fun getProductGratisProductOrder(scope: CoroutineScope,type:Int,order:String,sort:String): Flow<PagingData<ProductListPromotionProductDomainModel>>

    suspend fun getDetailOfficialStoreProductPromosi(scope: CoroutineScope,officialStoreId:Int): Flow<PagingData<ProductListPromotionProductDomainModel>>

    suspend fun getDetailOfficialStoreOrder(scope: CoroutineScope,order:String,sort:String,officialStoreId: Int): Flow<PagingData<ProductListPromotionProductDomainModel>>
    suspend fun getProductTebusMurah():Flow<List<ProductListTebusMurahDomainModel>>
    suspend fun getProductByName(name:String):Flow<List<ProductListDomainItemModel>>
    suspend fun getProductSearchProductOrder(scope: CoroutineScope,name:String,order:String,sort:String) : Flow<PagingData<ProductListPromotionProductDomainModel>>

    suspend fun getProductSearchProduct(scope: CoroutineScope,name:String) : Flow<PagingData<ProductListPromotionProductDomainModel>>

    suspend fun getBannerProduct(scope:CoroutineScope, bannerId:Int,order:String,sort:String,type:String):Flow<PagingData<ProductListPromotionProductDomainModel>>

}