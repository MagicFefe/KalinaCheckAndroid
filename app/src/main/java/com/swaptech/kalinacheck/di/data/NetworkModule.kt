package com.swaptech.kalinacheck.di.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.swaptech.kalinacheck.data.interceptor.AuthInterceptor
import com.swaptech.kalinacheck.presentation.utils.BASE_URL
import com.swaptech.kalinacheck.presentation.utils.network_error_handling.NetworkResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideAuthInterceptor(sharedPreferences: SharedPreferences): AuthInterceptor =
        AuthInterceptor(sharedPreferences)

    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(NetworkResponseCallAdapterFactory())
        .build()
}
