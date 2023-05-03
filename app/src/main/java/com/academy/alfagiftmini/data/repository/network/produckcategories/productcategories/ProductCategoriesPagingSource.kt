package com.academy.alfagiftmini.data.repository.network.produckcategories.productcategories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.repository.network.produckcategories.ProductCategoriesApiService
import com.academy.alfagiftmini.data.repository.network.produckcategories.model.ProductCategoriesDataModel
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel

class ProductCategoriesPagingSource (private val apiService: ProductCategoriesApiService): PagingSource<Int, ProductCategoriesDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductCategoriesDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductCategoriesDomainModel> {
        val position = params.key ?: 1
        return try {
            val response = getOnlineData(position)
            LoadResult.Page (
                data = response,
                nextKey = if (response.isEmpty()) null else position + 1,
                prevKey = null
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun getOnlineData(position: Int): List<ProductCategoriesDomainModel> {
        val data = apiService.getAllCategories(page = position, limit = 10)
        return ProductCategoriesDataModel.transform(data)
    }

}