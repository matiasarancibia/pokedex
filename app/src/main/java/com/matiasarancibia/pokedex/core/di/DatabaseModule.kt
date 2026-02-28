package com.matiasarancibia.pokedex.core.di

import android.content.Context
import androidx.room.Room
import com.matiasarancibia.pokedex.core.database.PokedexDatabase
import com.matiasarancibia.pokedex.core.database.dao.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providePokedexRoomDatabase(
        @ApplicationContext context: Context
    ): PokedexDatabase {
        return Room.databaseBuilder(
            context,
            PokedexDatabase::class.java,
            "pokedex.db"
        ).build()
    }

    @Provides
    fun provideAlertDao(db: PokedexDatabase): FavoritesDao = db.favoritesDao
}