package com.academy.alfagiftmini.domain.officialstore

import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface OfficialStoreDomainUseCase {

    suspend fun get14OfficialStore(): Flow<List<OfficialStoreDomainItemModel>>

    suspend fun getAllOfficialStore(scope: CoroutineScope): Flow<PagingData<OfficialStoreDomainItemModel>>

}