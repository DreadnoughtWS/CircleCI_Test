package com.academy.alfagiftmini.domain.officialstore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfficialStoreDomainItemModel(
    val id: Int,
    val name: String,
    val image:String,
    val productImage:String?,
    val brands:List<OfficialStorebrandsDomainItemModel>,
    val totalFollowers:Int
):Parcelable
