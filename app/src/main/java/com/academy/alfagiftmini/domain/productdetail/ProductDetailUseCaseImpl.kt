package com.academy.alfagiftmini.domain.productdetail

import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProductDetailUseCaseImpl @Inject constructor(private val repository:ProductDetailDomainRepository): ProductDetailUseCase {
    override suspend fun getProductDetail(productId: Int): Flow<ProductDetailResponseModel> {
        return repository.getProductDetail(productId)
    }

}