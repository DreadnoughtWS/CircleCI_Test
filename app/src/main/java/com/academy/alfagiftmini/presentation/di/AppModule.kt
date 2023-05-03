package com.academy.alfagiftmini.presentation.di

import com.academy.alfagiftmini.domain.banner.BannerDomainUseCase
import com.academy.alfagiftmini.domain.banner.BannerDomainUseCaseImpl
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCaseImpl
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCaseImpl
import com.academy.alfagiftmini.domain.productcategories.ProductCategoriesUseCase
import com.academy.alfagiftmini.domain.productcategories.ProductCategoriesUseCaseImpl
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCaseImpl
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCase
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideLoginDomainUseCase(useCaseImpl: LoginDomainUseCaseImpl): LoginDomainUseCase
    @Binds
    abstract fun provideRegisterDomainUseCase(useCaseImpl: RegisterDomainUseCaseImpl): RegisterDomainUseCase
    @Binds
    abstract fun provideOfficialStoreDomainUseCase(useCaseImpl: OfficialStoreDomainUseCaseImpl): OfficialStoreDomainUseCase
    @Binds
    abstract fun provideProductListDomainUseCase(useCaseImpl: ProductListDomainUseCaseImpl): ProductListDomainUseCase
    @Binds
    abstract fun provideBannerDomainUseCase(useCaseImpl: BannerDomainUseCaseImpl): BannerDomainUseCase
    @Binds
    abstract fun provideProductCategoriesUseCase(useCaseImpl: ProductCategoriesUseCaseImpl): ProductCategoriesUseCase

}