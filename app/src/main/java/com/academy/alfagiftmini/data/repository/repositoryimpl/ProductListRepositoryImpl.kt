package com.academy.alfagiftmini.data.repository.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.academy.alfagiftmini.data.repository.network.produklist.detailofficialstore.DetailOfficialStoreNamaDanTerlarisPagingSource
import com.academy.alfagiftmini.data.repository.netwok.produklist.pagingsource.detailofficialstore.DetailOfficialStorePromosiPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.gratisproduct.ProductListGratisProductNamaProductPagingSource
import com.academy.alfagiftmini.data.repository.netwok.produklist.pagingsource.gratisproduct.ProductListGratisProductPagingSource
import com.academy.alfagiftmini.data.repository.netwok.produklist.pagingsource.hargaspesial.ProductListHargaSpesialNamaProdukPagingSource
import com.academy.alfagiftmini.data.repository.netwok.produklist.pagingsource.hargaspesial.ProductListHargaSpesialPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.*
import com.academy.alfagiftmini.domain.produklist.ProductListDomainRepository
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val apiService: ProductListApiService,
) : ProductListDomainRepository {
    override suspend fun getAllProduct(
        scope: CoroutineScope, type: Int
    ): Flow<PagingData<ProductListDomainItemModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListHargaSpesialPagingSource(apiService, type)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductOrder(
        scope: CoroutineScope, type: Int, order: String, sort: String
    ): Flow<PagingData<ProductListDomainItemModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListHargaSpesialNamaProdukPagingSource(apiService, type, order, sort)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductGratisProduct(
        scope: CoroutineScope, type: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(5)) {
            ProductListGratisProductPagingSource(apiService, type)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductGratisProductOrder(
        scope: CoroutineScope, type: Int, order: String, sort: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListGratisProductNamaProductPagingSource(apiService, type, order, sort)
        }.flow.cachedIn(scope)
    }

    override suspend fun getDetailOfficialStoreProductPromosi(
        scope: CoroutineScope,
        officialStoreId: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config =  PagingConfig(10)){
            DetailOfficialStorePromosiPagingSource(apiService,officialStoreId)
        }.flow.cachedIn(scope)
    }

    override suspend fun getDetailOfficialStoreOrder(
        scope: CoroutineScope,
        order: String,
        sort: String,
        officialStoreId: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)){
            DetailOfficialStoreNamaDanTerlarisPagingSource(apiService,order,sort,officialStoreId)
        }.flow.cachedIn(scope)
    }

}