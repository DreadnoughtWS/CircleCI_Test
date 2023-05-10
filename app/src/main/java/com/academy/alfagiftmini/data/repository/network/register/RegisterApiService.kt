package com.academy.alfagiftmini.data.repository.network.register

import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterResponseModel
import retrofit2.Response
import retrofit2.http.*

interface RegisterApiService {
    @POST("register")
    suspend fun register(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        @Body body: RegisterDataModel
    ): Response<RegisterResponseModel>

    @GET("users")
    suspend fun checkAvailableEmail(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        @Query("email") email:String
    ): Response<List<RegisterDataModel>>
}