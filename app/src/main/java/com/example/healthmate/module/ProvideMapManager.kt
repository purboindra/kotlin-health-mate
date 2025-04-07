package com.example.healthmate.module

import android.content.Context
import com.example.healthmate.data.MapManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideMapManager {
    
    @Provides
    @Singleton
    fun provideMapManager(@ApplicationContext context: Context): MapManager {
        return MapManager(context = context)
    }
}