package com.academy.alfagiftmini.presentation.di

import com.academy.alfagiftmini.data.repository.netwok.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.data.repository.netwok.loginlogout.LoginDomainUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideLoginDomainUseCase(useCaseImpl: LoginDomainUseCaseImpl): LoginDomainUseCase

}