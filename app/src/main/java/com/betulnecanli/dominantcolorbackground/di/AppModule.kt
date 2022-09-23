package com.betulnecanli.dominantcolorbackground.di


import android.app.Application
import com.betulnecanli.dominantcolorbackground.network.ResultApi
import com.betulnecanli.dominantcolorbackground.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideURL() = Constants.URL

    @Provides
    @Singleton
    fun provideRetrofit(url : String): ResultApi =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ResultApi::class.java)


}