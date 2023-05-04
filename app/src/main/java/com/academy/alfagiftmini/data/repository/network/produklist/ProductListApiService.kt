package com.academy.alfagiftmini.data.repository.network.produklist

import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListTebusMurahDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListStockResponseDataModel
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


    //    detail official store
    @GET("products")
    suspend fun getDetailOfficialStoreProduct(
        @Query("status") status: Int = 1,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
        @Query("official_store_id") officialStoreIdL:Int
    ): List<ProductListDetailDataModel>

    @GET("products")
    suspend fun getDetailOfficialStoreOrder(
        @Query("status") status: Int = 1,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String,
        @Query("official_store_id") officialStoreIdL:Int
    ): List<ProductListDetailDataModel>

    @GET("promotion_product_502")
    suspend fun getTebusMurah():List<ProductListTebusMurahDataModel>

    @GET("products")
    suspend fun getMultipleProducts(
        @Query(encoded = true, value = "product_id") id: String,
    ):List<ProductListDetailDataModel>

    @GET("products")
    suspend fun getProductByName(
        @Query("status") status: Int = 1,
        @Query("q") name:String
    ):List<ProductListDetailDataModel>

    @GET("products")
    suspend fun getProductsBynameOrder(
        @Query("status") status: Int = 1,
        @Query("q") name:String,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String,
    ):List<ProductListDetailDataModel>

    @GET("products")
    suspend fun getProductsByBannerId(
        @Query("status") status: Int = 1,
        @Query("banner_id") bannerId:Int,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String,
        ):List<ProductListDetailDataModel>

    @GET("products")
    suspend fun getProductByCategories (
        @Query("product_category") category: String,
        @Query("product_sub_category") subCategory: String,
        @Query("_page") page: Int): ArrayList<ProductListDetailDataModel>

}