package com.academy.alfagiftmini.data.repository.repositoryimpl

import android.util.Log
import com.academy.alfagiftmini.data.repository.network.akun.AkunApiService
import com.academy.alfagiftmini.data.repository.network.akun.AkunDataModel
import com.academy.alfagiftmini.data.repository.network.akun.AkunEditModel
import com.academy.alfagiftmini.domain.akun.AkunDomainDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainEditDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainRepository
import com.academy.alfagiftmini.domain.akun.AkunResponseDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AkunRepositoryImpl @Inject constructor(private val akunApiService: AkunApiService) :
    AkunDomainRepository {
    override fun getAkunDetail(id: Int): Flow<AkunResponseDomain> {
        return flow {
            try {
                val response = akunApiService.getUserData(id = id)
                emit(
                    AkunResponseDomain(
                        "",
                        AkunDataModel.transformToModel(response.body()!![0]),
                        response.errorBody()?.string()
                    )
                )
            } catch (e: Exception) {
                emit(AkunResponseDomain(null, null, e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateAkun(editedAkunData: AkunDomainEditDataModel, id: Int) {
        try {
            Log.d("id", id.toString())
            Log.d("edit data", editedAkunData.toString())
            val transform = AkunEditModel.transform(editedAkunData)
            akunApiService.updateAkun(id = id, body = transform)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}