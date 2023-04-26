package com.academy.alfagiftmini.presentation.di

import com.academy.alfagiftmini.data.di.CoreComponent
import com.academy.alfagiftmini.presentation.authentication.activity.LoginActivity
import com.academy.alfagiftmini.presentation.homepage.activity.officialstore.AllOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.activity.officialstore.OfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.activity.productlist.ProductListGratisProductActivity
import com.academy.alfagiftmini.presentation.homepage.activity.productlist.ProductListHargaSpesialActivity
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
    fun officialStoreActivityInject(activity: OfficialStoreActivity)
    fun detailOfficialStoreActivityInject(activity: AllOfficialStoreActivity)
    fun productListHargaSepsialActivityInject(activity: ProductListHargaSpesialActivity)
    fun productListGratisProductActivityInject(activity: ProductListGratisProductActivity)
}