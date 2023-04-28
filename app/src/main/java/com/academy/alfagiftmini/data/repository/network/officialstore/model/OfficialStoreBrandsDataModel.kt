package com.academy.alfagiftmini.data.repository.network.officialstore.model

import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import com.google.gson.annotations.SerializedName

data class OfficialStoreBrandsDataModel(
    val brandId:Int?,
    @SerializedName("brand_name") val brandName:String?,
    @SerializedName("brand_image") val brandImage:String?
){
    companion object{
        fun transfomrs(models:List<OfficialStoreBrandsDataModel>):List<OfficialStorebrandsDomainItemModel>{
            return models.map {
                transform(it)
            }
        }

        private fun transform(model: OfficialStoreBrandsDataModel):OfficialStorebrandsDomainItemModel{
            return OfficialStorebrandsDomainItemModel(
                brandId = model.brandId,
                brandName = model.brandName,
                brandImage = model.brandImage
            )
        }
    }
}
