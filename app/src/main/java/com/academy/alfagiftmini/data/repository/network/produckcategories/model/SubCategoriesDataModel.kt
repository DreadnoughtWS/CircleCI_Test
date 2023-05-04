package com.academy.alfagiftmini.data.repository.network.produckcategories.model

import com.google.gson.annotations.SerializedName

data class SubCategoriesDataModel(
    @SerializedName("text")
    val text: String?
) {
    companion object {
        fun transform (it: List<SubCategoriesDataModel>?) : List<String> {
            return it?.map {
                it.text ?: ""
            } ?: listOf()
        }
    }
}
