package com.swaptech.kalinacheck.di.presentation

import com.swaptech.kalinacheck.di.presentation.activity.ActivityModule
import com.swaptech.kalinacheck.di.presentation.view_model.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class, ActivityModule::class
    ]
)
class PresentationModule
