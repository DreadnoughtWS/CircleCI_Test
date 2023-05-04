package com.academy.alfagiftmini.data.repository.network.officialstore

import android.provider.ContactsContract.Data
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.officialstore.model.OfficialStoreDetailDataModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel

class OfficialStoreListPagingSource(
    private val apiService: OfficialStoreApiService,
    private val name: String,
    private val type: String
) : PagingSource<Int, OfficialStoreDomainItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, OfficialStoreDomainItemModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OfficialStoreDomainItemModel> {
        val position = params.key ?: 1
        return try {
            var response: List<OfficialStoreDetailDataModel> = listOf()
            when (type) {
                DataUtils.TYPE_SEARCH_OFFICIAL -> {
                    response = apiService.getOfficialStoreByName(name, position, 10)
                }
                DataUtils.TYPE_GET_ALL_OFFICIAL -> {
                    response = apiService.getAllOfficialStore(position, 10)
                }
            }
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