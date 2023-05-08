package com.academy.alfagiftmini.data.repository.network.productdetail

import com.academy.alfagiftmini.data.repository.network.productdetail.model.ProductDetailResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDetailApiService {

    @GET("products")
    suspend fun getProductDetail(@Query("product_id") productId: Int
    ): ProductDetailResponseDataModel

}