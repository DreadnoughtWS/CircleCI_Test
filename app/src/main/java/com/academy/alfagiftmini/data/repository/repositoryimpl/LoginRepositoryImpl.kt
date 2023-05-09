package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.network.loginlogout.LoginApiService
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApiService: LoginApiService,
) : LoginDomainRepository {
    override fun login(user: LoginDataDomain): Flow<LoginResponseDomain> {
        return flow {
            try {
                val data = LoginDataDomain.transform(user)
                val response = loginApiService.login(body = data)
                emit(LoginResponseModel.transform(response.body() ?: LoginResponseModel("", RegisterDataModel(-1,"", "", "", "", "", ""), response.errorBody()?.string())))
            }catch (e : Exception) {
                emit(LoginResponseDomain("", RegisterDataDomain(-1, "", "", "", "", "", ""), e.message.toString()))
            }
        }.flowOn(IO)
    }

    override fun getUserData(id: Int): Flow<RegisterDataDomain> {
        return flow {
            try {
                val response = loginApiService.getUserData(id)
                emit(RegisterDataModel.transform(response))
            }catch (e : Exception) {
                emit(RegisterDataDomain(-1, "", "", "", "", "", ""))
            }
        }.flowOn(IO)
    }
}