package com.academy.alfagiftmini.data.repository.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.academy.alfagiftmini.data.repository.network.produckcategories.productcategories.ProductItemCategoryPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.detailofficialstore.DetailOfficialStoreNamaDanTerlarisPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.detailofficialstore.DetailOfficialStorePromosiPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.gratisproduct.ProductListGratisProductNamaProductPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.gratisproduct.ProductListGratisProductPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.*
import com.academy.alfagiftmini.data.repository.network.produklist.banner.BannerProductPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListTebusMurahDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.searchproduct.ProductListSearchProductNamaProdukPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.searchproduct.ProductListSearchProductPromosiPagingSource
import com.academy.alfagiftmini.domain.produklist.ProductListDomainRepository
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val apiService: ProductListApiService,
) : ProductListDomainRepository {

    override suspend fun getProductGratisProduct(
        scope: CoroutineScope, type: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListGratisProductPagingSource(apiService, type)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductGratisProductOrder(
        scope: CoroutineScope, type: Int, order: String, sort: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListGratisProductNamaProductPagingSource(apiService, type, order, sort)
        }.flow.cachedIn(scope)
    }

    override suspend fun getDetailOfficialStoreProductPromosi(
        scope: CoroutineScope, officialStoreId: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)) {
            DetailOfficialStorePromosiPagingSource(apiService, officialStoreId)
        }.flow.cachedIn(scope)
    }

    override suspend fun getDetailOfficialStoreOrder(
        scope: CoroutineScope, order: String, sort: String, officialStoreId: Int
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)) {
            DetailOfficialStoreNamaDanTerlarisPagingSource(apiService, order, sort, officialStoreId)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductTebusMurah(): Flow<List<ProductListTebusMurahDomainModel>> {
        return flow<List<ProductListTebusMurahDomainModel>> {
            try {
                val responseTebusMurah = apiService.getTebusMurah()

                val dataProductList: ArrayList<Long> = arrayListOf()
                responseTebusMurah.forEach { data ->
                    data.promotionProductId.map {
                        dataProductList.add(it)
                    }
                }

                val dataProductId = dataProductList.map {
                    it
                }.joinToString("&product_id=", "")

                println("DATA PRODUCT ID $dataProductId")
                val responseProduct = apiService.getMultipleProducts(id = dataProductId)
                emit(ProductListTebusMurahDataModel.transforms(responseTebusMurah, responseProduct))
            } catch (e: java.lang.Exception) {
                println(e.message)
                emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getProductByName(name: String): Flow<List<ProductListDomainItemModel>> {
        return flow<List<ProductListDomainItemModel>> {
            try {
                val responseProduct = apiService.getProductByName(name = name)
                emit(ProductListDetailDataModel.transforms(responseProduct, listOf()))
            } catch (e: java.lang.Exception) {
                println(e.message)
                emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getProductSearchProductOrder(
        scope: CoroutineScope, name: String, order: String, sort: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListSearchProductNamaProdukPagingSource(apiService, name, order, sort)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductSearchProduct(
        scope: CoroutineScope, name: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)) {
            ProductListSearchProductPromosiPagingSource(apiService, name)
        }.flow.cachedIn(scope)
    }

    override suspend fun getBannerProduct(
        scope: CoroutineScope, bannerId: Int, order: String, sort: String, type: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(10)) {
            BannerProductPagingSource(
                apiService = apiService,
                bannerId = bannerId,
                order = order,
                sort = sort,
                type = type
            )
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductByCategory(
        scope: CoroutineScope,
        subCategory: String,
        category: String,
        sort: String,
        order: String,
        type: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            ProductItemCategoryPagingSource(apiService, subCategory, category, sort, order, type)
        }.flow.cachedIn(scope)
    }

}