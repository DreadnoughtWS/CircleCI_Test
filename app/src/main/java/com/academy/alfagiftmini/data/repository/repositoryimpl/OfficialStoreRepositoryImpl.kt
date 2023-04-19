package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.netwok.officialstore.OfficialStoreApiService
import com.academy.alfagiftmini.data.repository.netwok.officialstore.model.OfficialStoreDetailDataModel
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

}