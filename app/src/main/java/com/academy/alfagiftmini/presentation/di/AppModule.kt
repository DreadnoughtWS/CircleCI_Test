package com.academy.alfagiftmini.presentation.di

import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCaseImpl
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCaseImpl
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideLoginDomainUseCase(useCaseImpl: LoginDomainUseCaseImpl): LoginDomainUseCase

    @Binds
    abstract fun provideOfficialStoreDomainUseCase(useCaseImpl: OfficialStoreDomainUseCaseImpl): OfficialStoreDomainUseCase
    @Binds
    abstract fun provideProductListDomainUseCase(useCaseImpl: ProductListDomainUseCaseImpl): ProductListDomainUseCase

}