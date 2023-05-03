package com.academy.alfagiftmini.data.repository.network.produckcategories

import com.academy.alfagiftmini.data.repository.network.produckcategories.model.ProductCategoriesDataModel
import retrofit2.http.GET

interface ProductCategoriesApiService {
    @GET("categories")
    suspend fun getAllCategories(): List<ProductCategoriesDataModel>
}