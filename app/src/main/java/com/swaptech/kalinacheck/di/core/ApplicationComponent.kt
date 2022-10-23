package com.swaptech.kalinacheck.di.core

import com.swaptech.kalinacheck.KalinaCheckApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class
    ]
)
interface ApplicationComponent: AndroidInjector<KalinaCheckApplication> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<KalinaCheckApplication>
}
