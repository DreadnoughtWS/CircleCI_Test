package com.academy.alfagiftmini.domain.officialstore

import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfficialStoreDomainUseCaseImpl @Inject constructor(private val repository: OfficialStoreDomainRepository) :
    OfficialStoreDomainUseCase {
    override suspend fun get14OfficialStore(
    ): Flow<List<OfficialStoreDomainItemModel>> {
        return repository.get14OfficialStore()
    }

    override suspend fun getAllOfficialStore(scope: CoroutineScope,name:String,type:String): Flow<PagingData<OfficialStoreDomainItemModel>> {
        return repository.getAllOfficialStore(scope,name,type)
    }

    override suspend fun getBrands(
        id: String
    ): Flow<List<OfficialStorebrandsDomainItemModel>> {
        return repository.getBrands(id)
    }


}