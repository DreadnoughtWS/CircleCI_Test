package com.academy.alfagiftmini.data.di

import android.content.Context
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import com.academy.alfagiftmini.domain.produklist.ProductListDomainRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideLoginRepository(): LoginDomainRepository
    fun provideOfficialStoreRepository(): OfficialStoreDomainRepository
    fun provideProductListRepository(): ProductListDomainRepository
}