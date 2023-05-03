package com.academy.alfagiftmini.presentation.di

import com.academy.alfagiftmini.data.di.CoreComponent
import com.academy.alfagiftmini.presentation.authentication.activity.LoginActivity
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.AllBannerListActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.AllOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.DetailOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.OfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories.ProductCategoriesActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.*
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class], modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun loginActivityInject(activity: LoginActivity)
    fun registerActivityInject(activity: RegisterActivity)
    fun officialStoreActivityInject(activity: OfficialStoreActivity)
    fun allOfficialStoreActivityInject(activity: AllOfficialStoreActivity)
    fun detailOfficialStoreActivityInject(activity: DetailOfficialStoreActivity)
    fun productListHargaSepsialActivityInject(activity: ProductListHargaSpesialActivity)
    fun productListGratisProductActivityInject(activity: ProductListGratisProductActivity)
    fun productListPaketActivityInject(activity: ProductListPaketActivity)
    fun productListTebusMurahActivityInject(activity: ProductListTebusMurahActivity)
    fun productListSearchProductActivityInject(activity: ProductListSearchProdukActivity)
    fun bannerListActivityInject(activity: AllBannerListActivity)

    fun productCategoriesActivityInject(activity: ProductCategoriesActivity)
}