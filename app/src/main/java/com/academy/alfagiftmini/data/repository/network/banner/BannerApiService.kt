package com.academy.alfagiftmini.data.repository.network.banner

import com.academy.alfagiftmini.data.repository.network.banner.model.BannerDataModel
import retrofit2.http.GET


interface BannerApiService {

    @GET("banners")
    suspend fun getAllBanners(): List<BannerDataModel>
}