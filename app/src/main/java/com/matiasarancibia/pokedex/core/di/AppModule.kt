package com.matiasarancibia.pokedex.core.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.matiasarancibia.pokedex.ui.util.AppResourcesManager
import com.matiasarancibia.pokedex.ui.util.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// In this Module we will add the utils dependency injections that will be used in the application
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(
        @ApplicationContext context: Context
    ): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideAppResourcesManager(
        @ApplicationContext context: Context
    ): AppResourcesManager {
        return AppResourcesManager(context)
    }

    @Provides
    @Singleton
    fun provideExoPlayer(
        @ApplicationContext context: Context
    ): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }
}