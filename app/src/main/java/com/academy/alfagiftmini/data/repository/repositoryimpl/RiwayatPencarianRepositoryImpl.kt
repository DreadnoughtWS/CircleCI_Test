package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.di.LocalModule
import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model.RiwayatPencarianDataModel
import com.academy.alfagiftmini.domain.riwayatpencarian.RiwayatPencarianRepository
import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class RiwayatPencarianRepositoryImpl @Inject constructor(private val dbService: LocalModule): RiwayatPencarianRepository {
    override suspend fun insertRiwayatPencarian(riwayatPencarianDomainModel: RiwayatPencarianDomainModel) {
        dbService.riwayatPencarianDao().insertPencarian(RiwayatPencarianDomainModel.transform(riwayatPencarianDomainModel))
    }

    override suspend fun getRiwayatPencarian(): Flow<List<RiwayatPencarianDomainModel>> {
        return flow {
            try {
                val result = dbService.riwayatPencarianDao().getData()
                emit(RiwayatPencarianDataModel.transformList(result))
            }catch (e: Exception) {
                emit(listOf())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteRiwayatPencarian() {
        dbService.riwayatPencarianDao().deletePencarian()
    }

}