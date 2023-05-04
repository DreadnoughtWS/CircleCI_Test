package com.academy.alfagiftmini.data.repository.network.produckcategories.productcategories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.repository.network.produckcategories.ProductCategoriesApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel

class ProductItemCategoryPagingSource(private val apiService: ProductCategoriesApiService, private val subCategory: String, private val category: String): PagingSource<Int, ProductListDomainItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductListDomainItemModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListDomainItemModel> {
        val position = params.key ?: 1
        return try {
            val data = getOnlineData(position)
            LoadResult.Page(
                data = data,
                nextKey = if (data.isEmpty()) null else position + 1,
                prevKey = null
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun getOnlineData(position: Int): List<ProductListDomainItemModel> {
        val response = apiService.getProductByCategories(category, subCategory, if (position == 1) 1 else position * 10 - 10)
        return ProductListDetailDataModel.transforms(response, listOf())
    }
}