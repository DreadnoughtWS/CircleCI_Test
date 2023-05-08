package com.academy.alfagiftmini.domain.productdetail

import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailResponseModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailDomainRepository {

    suspend fun getProductDetail(productId: Int): Flow<ProductDetailResponseModel>

}