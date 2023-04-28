package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.network.banner.BannerApiService
import com.academy.alfagiftmini.data.repository.network.banner.model.BannerDataModel
import com.academy.alfagiftmini.data.repository.network.banner.model.BannerResponseDataModel
import com.academy.alfagiftmini.data.repository.network.officialstore.model.OfficialStoreDetailDataModel
import com.academy.alfagiftmini.domain.banner.BannerDomainRepository
import com.academy.alfagiftmini.domain.banner.model.BannerResponseModel
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val ApiService: BannerApiService) : BannerDomainRepository {
    override suspend fun getallBanners(): Flow<BannerResponseModel> {
        return flow {
            try {
                val response = ApiService.getAllBanners()
                emit(BannerResponseDataModel.transformToDomainModel(response))
            } catch (E: Exception) {
                E.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }
}