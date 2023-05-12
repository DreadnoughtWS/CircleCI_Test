package com.academy.alfagiftmini.domain.riwayatpencarian

import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface RiwayatPencarianUseCase {
    suspend fun insertRiwayatPencarian(riwayatPencarianDomainModel: RiwayatPencarianDomainModel)

    suspend fun getRiwayatPencarian(): Flow<List<RiwayatPencarianDomainModel>>

    suspend fun deleteRiwayatPencarian()
}