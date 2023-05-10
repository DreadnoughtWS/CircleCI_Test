package com.academy.alfagiftmini.data.repository.repositoryimpl

import android.util.Log
import com.academy.alfagiftmini.data.repository.network.register.RegisterApiService
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.domain.register.RegisterDomainRepository
import com.academy.alfagiftmini.domain.register.RegisterResponseDomain
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerApiService: RegisterApiService,
) : RegisterDomainRepository {
    override fun register(newUser: RegisterDataDomain): Flow<RegisterResponseDomain> {
        return flow {
            try {
                val dataTransform = RegisterDataModel.transformToModel(newUser)
                Log.d("data", dataTransform.email)
                val response = registerApiService.register(body = dataTransform)
                emit(
                    RegisterResponseDomain(
                        response.body()?.accessToken,
                        RegisterDataModel.transform(response.body()?.user),
                        response.errorBody()?.string()
                    )
                )
            } catch (e: Exception) {
                emit(RegisterResponseDomain(null, null, "error"))
            }
        }.flowOn(IO)
    }

    override fun checkAvailableEmail(email: String): Flow<RegisterResponseDomain> {
        return flow {
            try {
                val response = registerApiService.checkAvailableEmail(email = email)
                emit(
                    RegisterResponseDomain(
                        "",
                        RegisterDataModel.transform(response.body()!![0]),
                        response.errorBody()?.string()
                    )
                )
            } catch (e: Exception) {
                emit(RegisterResponseDomain(null, null, e.message.toString()))
            }
        }.flowOn(IO)
    }
}