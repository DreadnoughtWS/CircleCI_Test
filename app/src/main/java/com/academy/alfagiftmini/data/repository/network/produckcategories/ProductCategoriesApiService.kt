package com.academy.alfagiftmini.data.repository.network.produckcategories

import com.academy.alfagiftmini.data.repository.network.produckcategories.model.ProductCategoriesDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductCategoriesApiService {
    @GET("categories")
    suspend fun getAllCategories(@Query("_page") page: Int, @Query("_limit") limit: Int): List<ProductCategoriesDataModel>

    @GET("products")
    suspend fun getProductByCategories (
        @Query("product_category") category: String,
        @Query("product_sub_category") subCategory: String,
        @Query("_page") page: Int): List<ProductListDetailDataModel>
}