package com.academy.alfagiftmini.domain.riwayatpencarian

import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RiwayatPencarianUseCaseImpl @Inject constructor(private val repository: RiwayatPencarianRepository): RiwayatPencarianUseCase{
    override suspend fun insertRiwayatPencarian(riwayatPencarianDomainModel: RiwayatPencarianDomainModel) {
        return repository.insertRiwayatPencarian(riwayatPencarianDomainModel)
    }

    override suspend fun getRiwayatPencarian(): Flow<List<RiwayatPencarianDomainModel>> {
        return repository.getRiwayatPencarian()
    }

    override suspend fun deleteRiwayatPencarian() {
        return repository.deleteRiwayatPencarian()
    }

}