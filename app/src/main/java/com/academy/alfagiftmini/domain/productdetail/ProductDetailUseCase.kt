package com.academy.alfagiftmini.domain.productdetail

import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.academy.alfagiftmini.domain.productdetail.model.ProductPromosi103DomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import kotlinx.coroutines.flow.Flow

interface ProductDetailUseCase {
    suspend fun getProductDetail(productId: Long): Flow<List<ProductDetailDomainModel>>
    suspend fun getProductGratis(productId: Long): Flow<List<ProductPromosi103DomainModel>>
}