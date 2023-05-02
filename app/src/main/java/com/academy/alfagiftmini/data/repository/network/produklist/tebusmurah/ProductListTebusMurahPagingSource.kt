package com.academy.alfagiftmini.data.repository.network.produklist.tebusmurah

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListTebusMurahDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel

//class ProductListTebusMurahPagingSource(
//    private val apiService: ProductListApiService,
//) : PagingSource<Int, ProductListTebusMurahDomainModel>() {
//    override fun getRefreshKey(state: PagingState<Int, ProductListTebusMurahDomainModel>): Int? {
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListTebusMurahDomainModel> {
//        val position = params.key ?:1
//        return try{
//            val responseTebusMurah = apiService.getTebusMurah()
//
////            val dataProductId:String = responseTebusMurah.map {
////                it.promotionProductId
////            }.joinToString("&product_id=","")
//
//            var dataProductId = ""
//            responseTebusMurah.forEach {data ->
//                dataProductId = data.promotionProductId.map {
//                    it
//                }.joinToString("&product_id=","")
//            }
//
//            val responseProduct = apiService.getMultipleProducts(id = dataProductId, page = position, limit = 10)
//
//            val dataSudahDiTransform = ProductListTebusMurahDataModel.transforms(respon)
//
//
//            toLoadResult(responseTebusMurah,nextKey = if (responseProduct.isEmpty()) null else position + 1)
//        }catch (e:java.lang.Exception){
//            println(e.message)
//            LoadResult.Error(e)
//        }
//    }
//
//    private fun toLoadResult(
//        data: List<ProductListTebusMurahDomainModel>,
//        prevKey: Int? = null,
//        nextKey: Int? = null
//    ): LoadResult<Int, ProductListTebusMurahDomainModel> {
//        return LoadResult.Page(
//            data = data, prevKey = prevKey, nextKey = nextKey
//        )
//    }
//
//}