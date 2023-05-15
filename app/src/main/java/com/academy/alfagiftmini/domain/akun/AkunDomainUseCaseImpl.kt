package com.academy.alfagiftmini.domain.akun

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AkunDomainUseCaseImpl @Inject constructor(private val repository: AkunDomainRepository): AkunDomainUseCase {
    override fun getAkunDetail(id: Int): Flow<AkunResponseDomain> {
        return repository.getAkunDetail(id)
    }

    override suspend fun updateAkun(editedAkunData: AkunDomainEditDataModel, id: Int) {
        return repository.updateAkun(editedAkunData, id)
    }
}