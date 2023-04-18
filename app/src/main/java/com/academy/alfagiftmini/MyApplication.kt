package com.academy.alfagiftmini

import android.app.Application
import com.academy.alfagiftmini.data.di.CoreComponent
import com.academy.alfagiftmini.data.di.DaggerCoreComponent
import com.academy.alfagiftmini.presentation.di.AppComponent
import com.academy.alfagiftmini.presentation.di.DaggerAppComponent

open class MyApplication : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}