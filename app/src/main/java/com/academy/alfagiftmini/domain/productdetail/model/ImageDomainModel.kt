package com.academy.alfagiftmini.domain.productdetail.model

import com.google.gson.annotations.SerializedName

data class ImageDomainModel(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: List<String>
)
