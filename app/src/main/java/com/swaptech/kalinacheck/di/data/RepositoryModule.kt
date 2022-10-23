package com.swaptech.kalinacheck.di.data

import com.swaptech.kalinacheck.data.auth.AuthRepositoryImpl
import com.swaptech.kalinacheck.domain.auth.AuthRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}
