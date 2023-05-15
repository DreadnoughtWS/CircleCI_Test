package com.academy.alfagiftmini.data.repository.network.akun

import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainEditDataModel
import retrofit2.Response
import retrofit2.http.*

interface AkunApiService {
    @GET("users")
    suspend fun getUserData(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        @Query("id") id:Int
    ): Response<List<AkunDataModel>>

    @PATCH("users/{id}")
    suspend fun updateAkun(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        @Path("id") id:Int,
        @Body body: AkunEditModel
    ): Response<AkunDataModel>
}