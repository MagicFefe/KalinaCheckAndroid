package com.swaptech.kalinacheck.di.presentation.activity

import com.swaptech.kalinacheck.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}