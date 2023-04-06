package com.example.gifgallery.di

import com.example.gifgallery.data.DefaultSearchRepository
import com.example.gifgallery.data.remote.DefaultDetailRepository
import com.example.gifgallery.data.remote.DefaultTrendingRepository
import com.example.gifgallery.domain.detail.DetailRepository
import com.example.gifgallery.domain.search.SearchRepository
import com.example.gifgallery.domain.trending.TrendingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchRepository(searchRepository: DefaultSearchRepository): SearchRepository

    @Binds
    abstract fun bindTrendingRepository(trendingRepository: DefaultTrendingRepository): TrendingRepository

    @Binds
    abstract fun bindDetailRepository(detailRepository: DefaultDetailRepository): DetailRepository
}