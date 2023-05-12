package com.academy.alfagiftmini.data.repository.network.akun

import com.academy.alfagiftmini.data.DataUtils
import retrofit2.Response
import retrofit2.http.*

interface AkunApiService {
    @GET("users")
    suspend fun getUserData(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        @Query("id") id:Int
    ): Response<List<AkunDataModel>>

    @DELETE("users/{id}")
    fun deleteAkun(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        id: Int
    )

    @PUT("users/{id}")
    fun updateAkun(
        @Header("Content-Type") header: String = DataUtils.CONTENT_TYPE,
        id: Int
    )
}