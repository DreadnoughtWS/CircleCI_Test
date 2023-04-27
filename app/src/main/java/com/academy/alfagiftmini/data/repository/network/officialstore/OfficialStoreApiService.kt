package com.academy.alfagiftmini.data.repository.network.officialstore

import com.academy.alfagiftmini.data.repository.network.officialstore.model.OfficialStoreDetailDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface OfficialStoreApiService {

    @GET("officialStores/")
    suspend fun getAllOfficialStore(
        @Query("_page") page: Int, @Query("_limit") limit: Int
    ): List<OfficialStoreDetailDataModel>

}