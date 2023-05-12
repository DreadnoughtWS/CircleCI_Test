package com.academy.alfagiftmini.domain.productdetail

import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.academy.alfagiftmini.domain.productdetail.model.ProductPromosi103DomainModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailDomainRepository {

    suspend fun getProductDetail(productId: Long): Flow<List<ProductDetailDomainModel>>
    suspend fun getProductGratis(productId: Long): Flow<List<ProductPromosi103DomainModel>>
}