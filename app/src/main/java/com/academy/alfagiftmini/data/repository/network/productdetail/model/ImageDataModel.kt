package com.academy.alfagiftmini.data.repository.network.productdetail.model

import android.media.Image
import com.academy.alfagiftmini.domain.productdetail.model.ImageDomainModel
import com.google.gson.annotations.SerializedName

data class ImageDataModel(
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: List<String>?
){
    companion object{
        fun transformToDomainList(item: List<ImageDataModel?>): List<ImageDomainModel>{
            return item.map {
                transformToDomainModel(it ?: ImageDataModel(
                    type = "",
                    url = listOf()
                ))
            }
        }

        private fun transformToDomainModel(it:ImageDataModel):ImageDomainModel{
            return ImageDomainModel(it.type ?: "", it.url ?: listOf() )
        }
    }
}
