package com.academy.alfagiftmini.data.repository.network.loginlogout

import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginDataModel
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService {
    @POST("login")
    suspend fun login(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        @Body body: LoginDataModel
    ): Response<LoginResponseModel>

    @GET("users")
    suspend fun getUserData(
        @Query("id") id: Int
    ): RegisterDataModel
}