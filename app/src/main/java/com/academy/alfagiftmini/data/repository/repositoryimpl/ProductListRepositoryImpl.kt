package com.academy.alfagiftmini.data.repository.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.academy.alfagiftmini.data.repository.netwok.officialstore.OfficialStoreApiService
import com.academy.alfagiftmini.data.repository.netwok.officialstore.OfficialStoreListPagingSource
import com.academy.alfagiftmini.data.repository.netwok.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.netwok.produklist.ProductListPagingSource
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import com.academy.alfagiftmini.domain.produklist.ProductListDomainRepository
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val ApiService: ProductListApiService,
) : ProductListDomainRepository {
    override suspend fun getAllProduct(
        scope: CoroutineScope,
        type: Int
    ): Flow<PagingData<ProductListDomainItemModel>> {
        return Pager(config = PagingConfig(10)){
            ProductListPagingSource(ApiService,type)
        }.flow.cachedIn(scope)
    }
}