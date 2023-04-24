package com.academy.alfagiftmini.domain.produklist

import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ProductListDomainRepository {

    suspend fun getAllProduct(scope: CoroutineScope,type:Int): Flow<PagingData<ProductListDomainItemModel>>


}