package com.academy.alfagiftmini.data.repository.network.produklist.searchproduct

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel

class ProductListSearchProductPromosiPagingSource(
    private val apiService: ProductListApiService,
    private val name: String,
    private val type: String
) : PagingSource<Int, ProductListPromotionProductDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductListPromotionProductDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListPromotionProductDomainModel> {
        val position = params.key ?: 1
        return try {

            val responseProduct: List<ProductListDetailDataModel>
            println("ENFAGROW")

            when (type) {
                DataUtils.TYPE_PRODUCT_NAME -> {
                    responseProduct = apiService.getProductTypeProductName(
                        page = position,
                        limit = 10,
                        name = name,
                        sort = "asc",
                        order = "product_name"
                    )
                }
                else -> {
                    responseProduct = apiService.getProductsBynameOrder(
                        page = position,
                        limit = 10,
                        name = name,
                        sort = "asc",
                        order = "product_name"
                    )
                }
            }

            val responseSale = apiService.getPromotionProduct()
            val responseStock = apiService.getProductStock()

            val dataProduct: ArrayList<ProductListDetailDataModel> = arrayListOf()


            for (data in responseProduct) {
                if ((data.productSpecialPrice ?: (data.price + 1)) < data.price) {
                    dataProduct.add(data)
                } else {
                    data.kodePromo?.forEach {
                        if (it == DataUtils.TYPE_GRATIS_PRODUK) {
                            dataProduct.add(data)
                        }
                    }
                }
            }

            val dataSudahDiTransform = ProductListPromotionProductDataModel.transforms(
                dataProduct, responseSale, responseStock[0].productDetails ?: arrayListOf()
            )
            toLoadResult(
                dataSudahDiTransform,
                nextKey = if (responseProduct.isEmpty()) null else position + 1
            )
        } catch (e: java.lang.Exception) {
            println(e.message)
            LoadResult.Error(e)
        }
    }

    private fun toLoadResult(
        data: List<ProductListPromotionProductDomainModel>,
        prevKey: Int? = null,
        nextKey: Int? = null
    ):LoadResult<Int, ProductListPromotionProductDomainModel> {
        return LoadResult.Page(
            data = data, prevKey = prevKey, nextKey = nextKey
        )
    }
}