package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.network.banner.BannerApiService
import com.academy.alfagiftmini.data.repository.network.banner.model.BannerDataModel
import com.academy.alfagiftmini.domain.banner.BannerDomainRepository
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.domain.banner.model.BannerResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val ApiService: BannerApiService) : BannerDomainRepository {
    override suspend fun getallBanners(): Flow<List<BannerDomainModel>> {
        return flow {
            try {
                val response = ApiService.getAllBanners()
                emit(BannerDataModel.transformToListDomainModel(response))
            } catch (E: Exception) {
                E.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }
}