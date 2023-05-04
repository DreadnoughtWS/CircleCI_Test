package com.academy.alfagiftmini.data.repository.network.produklist.banner

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils

class BannerProductPagingSource(
    private val apiService: ProductListApiService,
    private val bannerId: Int,
    private val sort: String,
    private val order: String,
    private val type: String

) : PagingSource<Int, ProductListPromotionProductDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductListPromotionProductDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListPromotionProductDomainModel> {
        val position = params.key ?: 1
        return try {
            val responseProduct = apiService.getProductsByBannerId(
                page = position,
                limit = 10,
                bannerId = bannerId,
                sort = sort,
                order = order
            )
            val responseSale = apiService.getPromotionProduct()
            val responseStock = apiService.getProductStock()

            val dataProduct: ArrayList<ProductListDetailDataModel> = arrayListOf()

            when (type) {
                PresentationUtils.TYPE_PROMOSI -> {
                    for (data in responseProduct) {
                        if (data.productSpecialPrice!! < data.price) {
                            dataProduct.add(data)
                        } else {
                            data.kodePromo?.forEach {
                                if (it == DataUtils.TYPE_GRATIS_PRODUK) {
                                    dataProduct.add(data)
                                }
                            }
                        }
                    }
                }

                PresentationUtils.TYPE_BUKAN_PROMOSI -> {
                    dataProduct.addAll(responseProduct)
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
    ): PagingSource.LoadResult<Int, ProductListPromotionProductDomainModel> {
        return PagingSource.LoadResult.Page(
            data = data, prevKey = prevKey, nextKey = nextKey
        )
    }
}