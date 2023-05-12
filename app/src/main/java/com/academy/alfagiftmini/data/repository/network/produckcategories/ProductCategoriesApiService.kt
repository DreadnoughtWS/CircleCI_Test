package com.academy.alfagiftmini.data.repository.network.produckcategories

import com.academy.alfagiftmini.data.repository.network.produckcategories.model.ProductCategoriesDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductCategoriesApiService {
    @GET("categories")
    suspend fun getAllCategories(@Query("_page") page: Int, @Query("_limit") limit: Int?): List<ProductCategoriesDataModel>
}