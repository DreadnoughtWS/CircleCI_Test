package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.network.productdetail.ProductDetailApiService
import com.academy.alfagiftmini.data.repository.network.productdetail.model.ProductDetailDataModel
import com.academy.alfagiftmini.domain.productdetail.ProductDetailDomainRepository
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val ApiService: ProductDetailApiService):ProductDetailDomainRepository {

    override suspend fun getProductDetail(productId: Long): Flow<List<ProductDetailDomainModel>> {
        return flow {
            try {
                val response = ApiService.getProductDetail(productId)
                emit(ProductDetailDataModel.transformToListDomainModel(response))
            } catch (E: Exception) {
                E.printStackTrace()
                emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
    }

}