package com.academy.alfagiftmini.data.repository.repositoryimpl

import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.academy.alfagiftmini.data.repository.network.officialstore.OfficialStoreApiService
import com.academy.alfagiftmini.data.repository.network.officialstore.OfficialStoreListPagingSource
import com.academy.alfagiftmini.data.repository.network.officialstore.model.OfficialStoreBrandsDataModel
import com.academy.alfagiftmini.data.repository.network.officialstore.model.OfficialStoreDetailDataModel
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.URLEncoder
import javax.inject.Inject

class OfficialStoreRepositoryImpl @Inject constructor(
    private val ApiService: OfficialStoreApiService,
) : OfficialStoreDomainRepository {
    override suspend fun get14OfficialStore(
    ): Flow<List<OfficialStoreDomainItemModel>> {
        return flow {
            try {
                val response = ApiService.getAllOfficialStore(1, 15)
                emit(OfficialStoreDetailDataModel.transforms(response))
            } catch (E: Exception) {
                emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllOfficialStore(scope: CoroutineScope): Flow<PagingData<OfficialStoreDomainItemModel>> {
        return Pager(config = PagingConfig(10)) {
            OfficialStoreListPagingSource(ApiService)
        }.flow.cachedIn(scope)
    }

    override suspend fun getBrands(
        id: String
    ): Flow<List<OfficialStorebrandsDomainItemModel>> {
        return flow {
            try {
                val response = ApiService.getBrands(id)
                emit(OfficialStoreBrandsDataModel.transfomrs(response))

            } catch (E: Exception) {
                println(E.message)
                emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
    }

}