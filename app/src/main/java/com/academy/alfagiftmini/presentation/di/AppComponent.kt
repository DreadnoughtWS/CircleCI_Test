package com.academy.alfagiftmini.presentation.di

import com.academy.alfagiftmini.data.di.CoreComponent
import com.academy.alfagiftmini.presentation.akun.EditAkunActivity
import com.academy.alfagiftmini.presentation.authentication.activity.LoginActivity
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.AllBannerListActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.BannerPromoItemListActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.AllOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.DetailOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.OfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.OfficialStoreSearchActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories.ProductCategoriesActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productdetail.ProductDetailActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productdetail.ProductDetailPromoGratisActivity
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

    fun mainActivityInject(activity: MainActivity)
    fun loginActivityInject(activity: LoginActivity)
    fun registerActivityInject(activity: RegisterActivity)
    fun officialStoreActivityInject(activity: OfficialStoreActivity)
    fun allOfficialStoreActivityInject(activity: AllOfficialStoreActivity)
    fun officialStoreSearchActivityInject(activity: OfficialStoreSearchActivity)
    fun detailOfficialStoreActivityInject(activity: DetailOfficialStoreActivity)
    fun productListHargaSepsialActivityInject(activity: ProductListHargaSpesialActivity)
    fun productListGratisProductActivityInject(activity: ProductListGratisProductActivity)
    fun productListPaketActivityInject(activity: ProductListPaketActivity)
    fun productListTebusMurahActivityInject(activity: ProductListTebusMurahActivity)
    fun productListSearchProductActivityInject(activity: ProductListSearchProdukActivity)
    fun productListPenawaranTerbaikActivityInject(activity: ProductListPenawaranTerbaikActivity)
    fun bannerListActivityInject(activity: AllBannerListActivity)
    fun productDetailActivityInject(activity: ProductDetailActivity)
    fun productGratisActivityInject(activity: ProductDetailPromoGratisActivity)
    fun bannerListPromoItemListActivityInject(activity: BannerPromoItemListActivity)
    fun productCategoriesActivityInject(activity: ProductCategoriesActivity)
    fun akunActivityInject(activity: EditAkunActivity)
}