package com.academy.alfagiftmini.domain.officialstore

import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import kotlinx.coroutines.flow.Flow

interface OfficialStoreDomainRepository {

    suspend fun get14OfficialStore(): Flow<List<OfficialStoreDomainItemModel>>


}