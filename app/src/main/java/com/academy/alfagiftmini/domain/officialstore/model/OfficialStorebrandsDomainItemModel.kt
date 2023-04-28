package com.academy.alfagiftmini.domain.officialstore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfficialStorebrandsDomainItemModel(
    val brandId:Int?,
     val brandName:String?,
    @SerializedName("brand_image") val brandImage:String?
):Parcelable
