package com.example.gifgallery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesAppScopeModule {

    @Singleton
    @AppCoroutineScope
    @Provides
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }
}

@InstallIn(ViewModelComponent::class)
@Module
object CoroutinesViewModelScopeModule {

    @ViewModelScoped
    @ViewModelCoroutineScope
    @Provides
    fun provideViewModelScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppCoroutineScope

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ViewModelCoroutineScope