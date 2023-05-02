package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.loginlogout.LoginApiService
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApiService: LoginApiService,
) : LoginDomainRepository {
    override fun login(user: LoginDataDomain): Flow<LoginResponseDomain> {
        return flow {
            try {
                val data = LoginDataDomain.transform(user)
                val jsonObject = JSONObject().put("email", data.email).put("password", data.password)
                val body = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(),jsonObject.toString())
                val response = loginApiService.login(body)
                emit(LoginResponseModel.transform(response))
            }catch (e : Exception) {
                emit(LoginResponseDomain("", RegisterDataDomain("", "", "", "", "", ""), e.message.toString()))
            }
        }.flowOn(IO)
    }
}