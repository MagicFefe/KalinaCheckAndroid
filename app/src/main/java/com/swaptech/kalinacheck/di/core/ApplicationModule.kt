package com.swaptech.kalinacheck.di.core

import android.content.Context
import com.swaptech.kalinacheck.KalinaCheckApplication
import com.swaptech.kalinacheck.di.data.DataModule
import com.swaptech.kalinacheck.di.presentation.PresentationModule
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        PresentationModule::class, DataModule::class
    ]
)
class ApplicationModule {

    @Provides
    fun provideContext(application: KalinaCheckApplication): Context =
        application.applicationContext
}