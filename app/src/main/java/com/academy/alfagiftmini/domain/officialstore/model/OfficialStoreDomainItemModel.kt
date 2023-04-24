package com.academy.alfagiftmini.domain.officialstore.model

import com.google.gson.annotations.SerializedName


data class OfficialStoreDomainItemModel(
    val id: Int,
    val name: String,
    val image:String,
    val productImage:String?,
    val brands:List<OfficialStorebrandsDomainItemModel>,
    val totalFollowers:Int
)
