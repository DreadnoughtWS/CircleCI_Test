package com.academy.alfagiftmini.domain.produklist

import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListDomainUseCaseImpl @Inject constructor(private val repository: ProductListDomainRepository) :
    ProductListDomainUseCase {

    override suspend fun getProductGratisProduct(
        scope: CoroutineScope, type: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return repository.getProductGratisProduct(scope, type)
    }

    override suspend fun getProductGratisProductOrder(
        scope: CoroutineScope, type: Int, order: String, sort: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        println("USECASEIMPL : $order")
        return repository.getProductGratisProductOrder(
            scope = scope, type = type, order = order, sort = sort
        )
    }

    override suspend fun getDetailOfficialStoreProductPromosi(
        scope: CoroutineScope, officialStoreId: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return repository.getDetailOfficialStoreProductPromosi(
            scope, officialStoreId
        )
    }

    override suspend fun getDetailOfficialStoreOrder(
        scope: CoroutineScope, order: String, sort: String, officialStoreId: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return repository.getDetailOfficialStoreOrder(
            scope, order, sort, officialStoreId
        )
    }

    override suspend fun getProductTebusMurah(): Flow<List<ProductListTebusMurahDomainModel>> {
        return repository.getProductTebusMurah()
    }

    override suspend fun getProductByName(name: String): Flow<List<ProductListDomainItemModel>> {
        return repository.getProductByName(name)
    }

    override suspend fun getProductSearchProductOrder(
        scope: CoroutineScope, name: String, order: String, sort: String, type: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return repository.getProductSearchProductOrder(scope, name, order, sort, type)
    }

    override suspend fun getProductSearchProduct(
        scope: CoroutineScope, name: String, type: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return repository.getProductSearchProduct(scope, name, type)
    }

    override suspend fun getBannerProduct(
        scope: CoroutineScope, bannerId: Int, order: String, sort: String, type: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return repository.getBannerProduct(
            scope = scope, bannerId = bannerId, order = order, sort = sort, type = type
        )
    }

    override suspend fun getProductByCategory(
        scope: CoroutineScope,
        subCategory: String,
        category: String,
        sort: String,
        order: String,
        type: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return repository.getProductByCategory(scope, subCategory, category, sort, order, type)
    }
}