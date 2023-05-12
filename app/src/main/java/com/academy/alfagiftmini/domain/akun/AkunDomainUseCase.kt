package com.academy.alfagiftmini.domain.akun

import kotlinx.coroutines.flow.Flow

interface AkunDomainUseCase {
    fun getAkunDetail(id: Int): Flow<AkunResponseDomain>
    fun deleteAkun(id: Int)
    fun updateAkun(id: Int)
}