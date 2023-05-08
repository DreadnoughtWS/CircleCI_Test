package com.academy.alfagiftmini.data.repository.network.productdetail.model

import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailResponseModel


data class ProductDetailResponseDataModel(
    val ProductList: List<ProductDetailDataModel?>?
){
    companion object{
        fun transformToEntity(it: ProductDetailResponseDataModel):ProductDetailResponseModel{
            return ProductDetailResponseModel(
                ProductDetailDataModel.transformToListDomainModel(it.ProductList ?: listOf())
            )
        }
    }
}
