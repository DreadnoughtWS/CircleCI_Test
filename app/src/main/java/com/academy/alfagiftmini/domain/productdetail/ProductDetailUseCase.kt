package com.academy.alfagiftmini.domain.productdetail

import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailResponseModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailUseCase {
    suspend fun getProductDetail(productId: Long): Flow<List<ProductDetailDomainModel>>
}