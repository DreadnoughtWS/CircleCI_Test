package com.academy.alfagiftmini.data.repository.network.register

import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginDataModel
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterResponseModel
import com.academy.alfagiftmini.domain.register.RegisterResponseDomain
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RegisterApiService {
    @POST("register")
    suspend fun register(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        @Body body: RegisterDataModel
    ): Response<RegisterResponseModel>
}