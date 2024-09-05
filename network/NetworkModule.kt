package com.laioffer.spotify.network

import android.net.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    private const val BASE_URL = "http://10.0.2.2:8080/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkApi(retrofit: Retrofit): NetworkApi{
        return retrofit.create(NetworkApi::class.java)
    }

}


// app -> make a call -> URL with "/feed" -> spotify backend_servers -> response with feed.json
// -> feed/app receive feed.json -> feed.json deserialize List<Section> -> use List<Section> render UI