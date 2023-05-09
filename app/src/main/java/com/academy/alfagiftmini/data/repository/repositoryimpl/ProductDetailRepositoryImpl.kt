package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.network.productdetail.ProductDetailApiService
import com.academy.alfagiftmini.data.repository.network.productdetail.model.ProductDetailResponseDataModel
import com.academy.alfagiftmini.domain.productdetail.ProductDetailDomainRepository
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val ApiService: ProductDetailApiService):ProductDetailDomainRepository {

    override suspend fun getProductDetail(productId: Int): Flow<ProductDetailResponseModel> {
        return flow {
            try {
                val response = ApiService.getProductDetail(productId)
                emit(ProductDetailResponseDataModel.transformToEntity(response))
            } catch (E: Exception) {
                E.printStackTrace()
                emit(ProductDetailResponseModel(emptyList()))
            }
        }.flowOn(Dispatchers.IO)
    }

}