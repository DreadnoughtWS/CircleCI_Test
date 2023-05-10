package com.academy.alfagiftmini.domain.banner

import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.domain.banner.model.BannerResponseModel
import kotlinx.coroutines.flow.Flow

interface BannerDomainRepository {

    suspend fun getallBanners(): Flow<List<BannerDomainModel>>
}