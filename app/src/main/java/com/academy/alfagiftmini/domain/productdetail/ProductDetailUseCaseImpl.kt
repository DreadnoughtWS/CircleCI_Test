package com.academy.alfagiftmini.domain.productdetail

import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.academy.alfagiftmini.domain.productdetail.model.ProductPromosi103DomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProductDetailUseCaseImpl @Inject constructor(private val repository:ProductDetailDomainRepository): ProductDetailUseCase {
    override suspend fun getProductDetail(productId: Long): Flow<List<ProductDetailDomainModel>>  {
        return repository.getProductDetail(productId)
    }

    override suspend fun getProductGratis(productId: Long): Flow<List<ProductPromosi103DomainModel>> {
        return repository.getProductGratis(productId)
    }

}