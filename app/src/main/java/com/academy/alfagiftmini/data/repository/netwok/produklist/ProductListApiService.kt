package com.academy.alfagiftmini.data.repository.netwok.produklist

import com.academy.alfagiftmini.data.repository.netwok.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.netwok.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.data.repository.netwok.produklist.model.ProductListStockResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductListApiService {

    @GET("products")
    suspend fun getAllProduct(
        @Query("status") status: Int = 1, @Query("_page") page: Int, @Query("_limit") limit: Int
    ): List<ProductListDetailDataModel>

    @GET("products")
    suspend fun getProductsOrder(
        @Query("status") status: Int = 1,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): List<ProductListDetailDataModel>

    @GET("products_stock")
    suspend fun getProductStock(
        @Query("kodeArea") status: String = "A01",
    ): List<ProductListStockResponseDataModel>

    @GET("promotion_product_103")
    suspend fun getPromotionProduct(
    ): List<ProductListPromotionProductDataModel>


}