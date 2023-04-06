package com.example.gifgallery.di

import com.example.gifgallery.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "http://api.giphy.com/v1/"

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = getHttpClient()
}

private fun getHttpClient(): OkHttpClient {
    val apiKey = "sCjn2dU8xZKhJQdu7K5CzC3JE4sUlOyg"
    val interceptor = Interceptor { chain ->
        val url: HttpUrl = chain.request().url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val request = chain.request().newBuilder().url(url).build()
        chain.proceed(request)
    }

    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}