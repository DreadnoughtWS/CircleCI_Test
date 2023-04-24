package com.academy.alfagiftmini.domain.produklist

import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListDomainUseCaseImpl @Inject constructor(private val repository: ProductListDomainRepository) :
    ProductListDomainUseCase {
    override suspend fun getAllProduct(
        scope: CoroutineScope,
        type: Int
    ): Flow<PagingData<ProductListDomainItemModel>> {
        return repository.getAllProduct(scope,type)
    }
}