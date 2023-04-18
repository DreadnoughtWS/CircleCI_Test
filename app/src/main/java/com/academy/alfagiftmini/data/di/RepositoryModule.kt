package com.academy.alfagiftmini.data.di

import com.academy.alfagiftmini.data.repository.domainrepositoryimpl.LoginDomainRepositoryImpl
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideLoginRepository(repositoryImpl: LoginDomainRepositoryImpl): LoginDomainRepository
}