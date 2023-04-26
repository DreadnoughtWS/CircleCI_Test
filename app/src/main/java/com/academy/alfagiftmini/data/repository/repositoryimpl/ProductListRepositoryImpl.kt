package com.academy.alfagiftmini.data.repository.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.academy.alfagiftmini.data.repository.netwok.produklist.*
import com.academy.alfagiftmini.domain.produklist.ProductListDomainRepository
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cache
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val ApiService: ProductListApiService,
) : ProductListDomainRepository {
    override suspend fun getAllProduct(
        scope: CoroutineScope, type: Int
    ): Flow<PagingData<ProductListDomainItemModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListHargaSpesialPagingSource(ApiService, type)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductOrder(
        scope: CoroutineScope, type: Int, order: String, sort: String
    ): Flow<PagingData<ProductListDomainItemModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListHargaSpesialNamaProdukPagingSource(ApiService, type, order, sort)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductGratisProduct(
        scope: CoroutineScope, type: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(5)) {
            ProductListGratisProductPagingSource(ApiService, type)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductGratisProductOrder(
        scope: CoroutineScope, type: Int, order: String, sort: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListGratisProductNamaProductPagingSource(ApiService, type, order, sort)
        }.flow.cachedIn(scope)
    }
}