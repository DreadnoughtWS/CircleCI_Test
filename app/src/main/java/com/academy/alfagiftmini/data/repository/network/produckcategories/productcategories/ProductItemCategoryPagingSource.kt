package com.academy.alfagiftmini.data.repository.network.produckcategories.productcategories

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.produckcategories.ProductCategoriesApiService
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.productcategories.ProductCategoriesRepository
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel

class ProductItemCategoryPagingSource(
    private val apiService: ProductListApiService,
    private val categoriesApiService: ProductCategoriesApiService,
    private val subCategory: String,
    private val category: String,
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

            val responseProduct = categoriesApiService.getProductByCategories(
                page = position,
                subCategory = subCategory,
                category = category,
                sort = sort,
                order = order
            )
            val responseSale = apiService.getPromotionProduct()
            val responseStock = apiService.getProductStock()

            val dataProduct: ArrayList<ProductListDetailDataModel> = arrayListOf()

            when (type) {
                DataUtils.TYPE_PROMOSI -> {
                    for (data in responseProduct) {
                        if (data.productSpecialPrice == null) {
                            continue
                        }
                        if (data.productSpecialPrice < data.price) {
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

                DataUtils.TYPE_BUKAN_PROMOSI -> {
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