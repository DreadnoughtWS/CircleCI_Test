package com.academy.alfagiftmini.domain.officialstore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfficialStorebrandsDomainItemModel(
    val brandId:Int?,
     val brandName: String?,
    val brandImage:String?
):Parcelable
