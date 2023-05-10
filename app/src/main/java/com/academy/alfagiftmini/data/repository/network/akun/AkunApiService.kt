package com.academy.alfagiftmini.data.repository.network.akun

import com.academy.alfagiftmini.data.DataUtils
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

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
}