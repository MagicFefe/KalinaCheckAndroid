package com.swaptech.kalinacheck.di.presentation.view_model

import androidx.lifecycle.ViewModel
import com.swaptech.kalinacheck.presentation.screens.attachments.AttachmentsScreenViewModel
import com.swaptech.kalinacheck.presentation.screens.landing.LandingScreenViewModel
import com.swaptech.kalinacheck.presentation.view_model.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @CreateWithViewModelFactory(LandingScreenViewModel::class)
    fun bindLandingScreenViewModel(landingScreenViewModel: LandingScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @CreateWithViewModelFactory(AttachmentsScreenViewModel::class)
    fun bindAttachmentsScreenViewModel(attachmentsScreenViewModel: AttachmentsScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @CreateWithViewModelFactory(AuthViewModel::class)
    fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel
}
