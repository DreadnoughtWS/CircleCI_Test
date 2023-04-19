package com.academy.alfagiftmini.data.repository.netwok.officialstore.model

import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel

data class OfficialStoreDetailDataModel(
    val id: Int?,
    val name: String?,
    val image: String?,
    val brands: List<OfficialStoreBrandsDataModel>?,
    val totalFollowers: Int?
) {
    companion object {
        fun transforms(models: List<OfficialStoreDetailDataModel>?): List<OfficialStoreDomainItemModel> {
            if (models != null) {
                return models.map {
                    transform(
                        OfficialStoreDetailDataModel(
                            id = it.id,
                            name = it.name,
                            image = it.image,
                            brands = it.brands,
                            totalFollowers = it.totalFollowers
                        )
                    )
                }
            } else {
                return listOf()
            }
        }

        private fun transform(model: OfficialStoreDetailDataModel): OfficialStoreDomainItemModel {
            return OfficialStoreDomainItemModel(
                id = model.id ?: 0,
                name = model.name ?: "",
                image = model.image ?: "",
                brands = model.brands?.map {
                    OfficialStorebrandsDomainItemModel(
                        brandId = it.brandId,
                        brandName = it.brandName
                    )
                } ?: listOf(),
                totalFollowers = model.totalFollowers ?: 0
            )
        }
    }
}
