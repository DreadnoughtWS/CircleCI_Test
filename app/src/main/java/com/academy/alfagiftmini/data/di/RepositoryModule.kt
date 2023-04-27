package com.academy.alfagiftmini.data.di

import com.academy.alfagiftmini.data.repository.repositoryimpl.LoginRepositoryImpl
import com.academy.alfagiftmini.data.repository.repositoryimpl.OfficialStoreRepositoryImpl
import com.academy.alfagiftmini.data.repository.repositoryimpl.ProductListRepositoryImpl
import com.academy.alfagiftmini.data.repository.repositoryimpl.RegisterRepositoryImpl
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import com.academy.alfagiftmini.domain.produklist.ProductListDomainRepository
import com.academy.alfagiftmini.domain.register.RegisterDomainRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideLoginRepository(repositoryImpl: LoginRepositoryImpl): LoginDomainRepository
    @Binds
    abstract fun provideRegisterRepository(repositoryImpl: RegisterRepositoryImpl): RegisterDomainRepository
    @Binds
    abstract fun provideOfficialStoreRepository(repositoryImpl: OfficialStoreRepositoryImpl): OfficialStoreDomainRepository
    @Binds
    abstract fun provideProductListRepository(repositoryImpl: ProductListRepositoryImpl): ProductListDomainRepository
}