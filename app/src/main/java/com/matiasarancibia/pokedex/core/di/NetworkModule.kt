package com.matiasarancibia.pokedex.core.di

import android.content.Context
import com.matiasarancibia.pokedex.core.network.NetworkErrorManager
import com.matiasarancibia.pokedex.domain.mappers.APIErrorVDMapper
import com.matiasarancibia.pokedex.domain.repository.PokedexApiService
import com.matiasarancibia.pokedex.ui.util.AppResourcesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*
    In this Module we will add the network-related dependency injections
    that will be used in the application
*/
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        /*
            Here we declare some important constants to be added to the OkHttp client to
            handle the connection timeouts and avoid an infinite loading time
         */
        private const val CONNECT_TIMEOUT = 60L
        private const val READ_TIMEOUT = 60L
        private const val WRITE_TIMEOUT = 60L
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        // Here we add the logging interceptor to see the request and response logs by Logcat
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): PokedexApiService {
        return retrofit.create(PokedexApiService::class.java)
    }

    /*
        Here we declare the dependency injection for the APIErrorVDMapper to be used
        into the NetworkErrorManager
     */
    @Provides
    @Singleton
    fun provideAPIErrorVDMapper(
        appResourcesManager: AppResourcesManager
    ): APIErrorVDMapper {
        return APIErrorVDMapper(appResourcesManager)
    }

    /*
        Here we declare the dependency injection for the NetworkErrorManager to be used
        later in the View Models
     */
    @Provides
    @Singleton
    fun provideNetworkErrorManager(
        @ApplicationContext context: Context,
        apiErrorVDMapper: APIErrorVDMapper
    ): NetworkErrorManager {
        return NetworkErrorManager(context, apiErrorVDMapper)
    }
}