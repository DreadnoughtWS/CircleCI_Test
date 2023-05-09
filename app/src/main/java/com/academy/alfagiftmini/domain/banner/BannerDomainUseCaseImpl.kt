package com.academy.alfagiftmini.domain.banner

import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.domain.banner.model.BannerResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BannerDomainUseCaseImpl@Inject constructor(private val repository: BannerDomainRepository) : BannerDomainUseCase{
    override suspend fun getallBanners(): Flow<List<BannerDomainModel>> {
        return repository.getallBanners()
    }
}