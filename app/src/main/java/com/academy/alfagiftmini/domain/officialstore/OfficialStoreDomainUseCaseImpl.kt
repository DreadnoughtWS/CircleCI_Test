package com.academy.alfagiftmini.domain.officialstore

import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfficialStoreDomainUseCaseImpl @Inject constructor(private val repository: OfficialStoreDomainRepository) :
    OfficialStoreDomainUseCase {
    override suspend fun get14OfficialStore(
    ): Flow<List<OfficialStoreDomainItemModel>> {
        return repository.get14OfficialStore()
    }


}