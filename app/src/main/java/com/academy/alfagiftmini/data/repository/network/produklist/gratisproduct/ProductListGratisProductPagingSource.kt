package com.academy.alfagiftmini.data.repository.network.produklist.gratisproduct

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.DataUtils.TYPE_GRATIS_PRODUK
import com.academy.alfagiftmini.data.DataUtils.TYPE_HARGA_SPESIAL
import com.academy.alfagiftmini.data.DataUtils.TYPE_PAKET
import com.academy.alfagiftmini.data.DataUtils.TYPE_TEBUS_MURAH
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel

class ProductListGratisProductPagingSource(
    private val apiService: ProductListApiService, private val type: Int
) : PagingSource<Int, ProductListPromotionProductDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductListPromotionProductDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListPromotionProductDomainModel> {
        val position = params.key ?: 1
        return try {
            val responseProduct = apiService.getAllProduct(page = position, limit = 10)
            val responseSale = apiService.getPromotionProduct()
            val responseStock = apiService.getProductStock()

            val dataKodePromo: ArrayList<ProductListDetailDataModel> = arrayListOf()

            when (type) {
                TYPE_HARGA_SPESIAL -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == TYPE_HARGA_SPESIAL) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                TYPE_PAKET -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == TYPE_PAKET) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                TYPE_GRATIS_PRODUK -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == TYPE_GRATIS_PRODUK) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                TYPE_TEBUS_MURAH -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == TYPE_TEBUS_MURAH) {
                                dataKodePromo.add(it)
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
        } catch (e: java.lang.Exception) {
            println(e.message)
            LoadResult.Error(e)
        }
    }

    private fun toLoadResult(
        data: List<ProductListPromotionProductDomainModel>,
        prevKey: Int? = null,
        nextKey: Int? = null
    ): LoadResult<Int, ProductListPromotionProductDomainModel> {
        return LoadResult.Page(
            data = data, prevKey = prevKey, nextKey = nextKey
        )
    }


}