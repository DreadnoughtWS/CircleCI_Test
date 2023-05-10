package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.network.akun.AkunApiService
import com.academy.alfagiftmini.data.repository.network.akun.AkunDataModel
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

    override fun deleteAkun(id: Int) {
        try {
            akunApiService.deleteAkun(id = id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}