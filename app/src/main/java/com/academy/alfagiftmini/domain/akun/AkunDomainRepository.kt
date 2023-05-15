package com.academy.alfagiftmini.domain.akun

import kotlinx.coroutines.flow.Flow

interface AkunDomainRepository {
    fun getAkunDetail(id: Int): Flow<AkunResponseDomain>
    suspend fun updateAkun(editedAkunData: AkunDomainEditDataModel, id: Int)
}