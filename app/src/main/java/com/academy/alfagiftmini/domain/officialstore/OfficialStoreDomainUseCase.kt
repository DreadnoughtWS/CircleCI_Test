package com.academy.alfagiftmini.domain.officialstore

import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import kotlinx.coroutines.flow.Flow

interface OfficialStoreDomainUseCase {

    suspend fun get14OfficialStore(): Flow<List<OfficialStoreDomainItemModel>>

}