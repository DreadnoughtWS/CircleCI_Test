package com.academy.alfagiftmini.presentation.di

import com.academy.alfagiftmini.data.di.CoreComponent
import com.academy.alfagiftmini.presentation.authentication.activity.LoginActivity
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
}