package com.academy.alfagiftmini.data.repository.network.banner.model

import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.google.gson.annotations.SerializedName


data class BannerDataModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("bannerName")
    val bannerName : String?,
    @SerializedName("bannerImageFileName")
    val bannerImageFileName: String?,
    @SerializedName("startDate")
    val startDate: String?,
    @SerializedName("endDate")
    val endDate: String?,
    @SerializedName("deeplink")
    val deepLink: String?,
    @SerializedName("syarat_ketentuan")
    val syaratKetentuan: String?
){
    companion object{
        fun transformToListDomainModel(item: List<BannerDataModel?>):List<BannerDomainModel>{
            return item.map {
                transformToDomainModel(it ?: BannerDataModel(
                        id = 0,
                        bannerName = "",
                        bannerImageFileName =  "",
                        startDate = "",
                        endDate = "",
                        deepLink = "",
                        syaratKetentuan = ""
                    )
                )
            }
        }

        private fun transformToDomainModel(it:BannerDataModel): BannerDomainModel {
            return  BannerDomainModel(
                id = it.id ?: 0,
                bannerName = it.bannerName ?: "",
                bannerImageFileName = it.bannerImageFileName ?: "",
                startDate = it.startDate ?: "",
                endDate = it.endDate ?: "",
                deepLink = it.deepLink ?: "",
                syaratKetentuan = it.syaratKetentuan ?: ""
            )
        }

    }
}
