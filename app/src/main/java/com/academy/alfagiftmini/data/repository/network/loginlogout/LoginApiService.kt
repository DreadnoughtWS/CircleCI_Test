package com.academy.alfagiftmini.data.repository.network.loginlogout

import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginDataModel
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApiService {
    @POST("login")
    suspend fun login(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        @Body body: LoginDataModel
    ): LoginResponseModel
}