package com.academy.alfagiftmini.data.repository.network.productdetail

import com.academy.alfagiftmini.data.repository.network.productdetail.model.ProductDetailDataModel
import com.academy.alfagiftmini.data.repository.network.productdetail.model.ProductPromosi103DataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDetailApiService {

    @GET("products")
    suspend fun getProductDetail(@Query("product_id") productId: Long
    ): List<ProductDetailDataModel>

    @GET("promotion_product_103")
    suspend fun getPromotionProduct(@Query("product_id") productId: Long
    ): List<ProductPromosi103DataModel>
}