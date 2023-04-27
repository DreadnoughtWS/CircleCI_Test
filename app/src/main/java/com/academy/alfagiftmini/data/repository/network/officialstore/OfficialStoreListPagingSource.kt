package com.academy.alfagiftmini.data.repository.network.officialstore

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.repository.network.officialstore.model.OfficialStoreDetailDataModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel

class OfficialStoreListPagingSource(
    private val apiService: OfficialStoreApiService,
) : PagingSource<Int, OfficialStoreDomainItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, OfficialStoreDomainItemModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OfficialStoreDomainItemModel> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getAllOfficialStore(position, 10)
            val data = OfficialStoreDetailDataModel.transforms(response)

            toLoadResult(
                data = data, nextKey = if (data.isEmpty()) null else position + 1
            )

        } catch (e: java.lang.Exception) {
            println(e.message)
            LoadResult.Error(e)
        }
    }

    private fun toLoadResult(
        data: List<OfficialStoreDomainItemModel>, prevKey: Int? = null, nextKey: Int? = null
    ): LoadResult<Int, OfficialStoreDomainItemModel> {
        return LoadResult.Page(
            data = data, prevKey = prevKey, nextKey = nextKey
        )
    }
}