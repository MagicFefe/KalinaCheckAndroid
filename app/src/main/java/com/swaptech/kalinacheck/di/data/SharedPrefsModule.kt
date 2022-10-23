package com.swaptech.kalinacheck.di.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.swaptech.kalinacheck.presentation.utils.ENCRYPTED_SHARED_PREFS_FILENAME
import dagger.Module
import dagger.Provides

@Module
class SharedPrefsModule {

    @Provides
    fun provideEncryptedSharedPrefs(context: Context): SharedPreferences =
        EncryptedSharedPreferences.create(
            ENCRYPTED_SHARED_PREFS_FILENAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
}