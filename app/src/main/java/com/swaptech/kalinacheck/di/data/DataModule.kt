package com.swaptech.kalinacheck.di.data

import dagger.Module

@Module(
    includes = [
        RepositoryModule::class, SharedPrefsModule::class, NetworkModule::class, ApiModule::class
    ]
)
class DataModule
