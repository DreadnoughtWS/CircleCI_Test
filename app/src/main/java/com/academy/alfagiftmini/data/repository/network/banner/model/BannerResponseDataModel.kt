package com.academy.alfagiftmini.data.repository.network.banner.model

import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.domain.banner.model.BannerResponseModel

data class BannerResponseDataModel(
    val bannerList: List<BannerDataModel?>?
){
    companion object{
        fun transformToDomainModel(it:BannerResponseDataModel): BannerResponseModel{
            return BannerResponseModel(
                BannerDataModel.transformToListDomainModel(it.bannerList ?: listOf())
            )
        }

    }
}
