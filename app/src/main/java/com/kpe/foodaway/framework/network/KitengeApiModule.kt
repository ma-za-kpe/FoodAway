package com.kpe.foodaway.framework.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "http://www.kiteng3.com/"

@Module
class KitengeApiModule {

//    @Provides
//    @Singleton
//    fun provideInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            val newUrl = chain.request().url()
//                .newBuilder()
//                .addQueryParameter("api_key", TMDb_API_KEY)
//                .build()
//
//            val newRequest = chain.request()
//                .newBuilder()
//                .url(newUrl)
//                .build()
//
//            chain.proceed(newRequest)
//        }
//    }

    @Provides
    @Singleton
    fun provideKitengeApiClient(@Named authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, randomUserApiClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(randomUserApiClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesKitengeApi(retrofit: Retrofit): KitengeService {
        return retrofit.create(KitengeService::class.java)
    }
}