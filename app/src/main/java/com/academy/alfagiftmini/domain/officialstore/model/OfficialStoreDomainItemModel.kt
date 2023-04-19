package com.academy.alfagiftmini.domain.officialstore.model


data class OfficialStoreDomainItemModel(
    val id: Int,
    val name: String,
    val image:String,
    val brands:List<OfficialStorebrandsDomainItemModel>,
    val totalFollowers:Int
)
