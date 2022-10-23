package com.swaptech.kalinacheck

import com.swaptech.kalinacheck.di.core.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class KalinaCheckApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.factory().create(this)
}
