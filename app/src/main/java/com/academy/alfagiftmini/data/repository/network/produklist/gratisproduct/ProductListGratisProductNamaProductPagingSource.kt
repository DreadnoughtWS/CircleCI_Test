package com.academy.alfagiftmini.data.repository.network.produklist.gratisproduct

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel

class ProductListGratisProductNamaProductPagingSource(
    private val apiService: ProductListApiService,
    private val type: Int,
    private val orderBy: String,
    private val sort: String
) : PagingSource<Int, ProductListPromotionProductDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductListPromotionProductDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListPromotionProductDomainModel> {
        val position = params.key ?: 1
        return try {
            val responseStock = apiService.getProductStock()
            val responseSale = apiService.getPromotionProduct()

            val responseProduct = apiService.getProductsOrder(
                page = position, limit = 10, order = orderBy, sort = sort
            )

            val dataKodePromo: ArrayList<ProductListDetailDataModel> = arrayListOf()

            when (type) {
                DataUtils.TYPE_HARGA_SPESIAL -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == DataUtils.TYPE_HARGA_SPESIAL) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                DataUtils.TYPE_PAKET -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == DataUtils.TYPE_PAKET) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                DataUtils.TYPE_GRATIS_PRODUK -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == DataUtils.TYPE_GRATIS_PRODUK) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                DataUtils.TYPE_TEBUS_MURAH -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == DataUtils.TYPE_TEBUS_MURAH) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                DataUtils.TYPE_PENAWARAN_TERBAIK -> {
                    
                    for (data in responseProduct) {
                        if (data.productSpecialPrice == null) {
                            continue
                        }
                        if (data.productSpecialPrice < data.price) {
                            dataKodePromo.add(data)
                        } else {
                            data.kodePromo?.forEach {
                                if (it == DataUtils.TYPE_GRATIS_PRODUK) {
                                    dataKodePromo.add(data)
                                }
                            }
                        }

                    }
                }
            }

            val dataSudahDiTransform = ProductListPromotionProductDataModel.transforms(
                dataKodePromo, responseSale, responseStock[0].productDetails ?: arrayListOf()
            )

            toLoadResult(
                dataSudahDiTransform,
                nextKey = if (responseProduct.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
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