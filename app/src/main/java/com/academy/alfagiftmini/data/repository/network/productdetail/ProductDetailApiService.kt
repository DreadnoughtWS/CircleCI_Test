package com.academy.alfagiftmini.data.repository.network.productdetail

import com.academy.alfagiftmini.data.repository.network.productdetail.model.ProductDetailDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDetailApiService {

    @GET("products")
    suspend fun getProductDetail(@Query("product_id") productId: Long
    ): List<ProductDetailDataModel>

}