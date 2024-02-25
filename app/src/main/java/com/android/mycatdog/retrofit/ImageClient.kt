package com.android.mycatdog.retrofit

import com.android.mycatdog.apidata.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ImageClient {
    private fun createOkHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()
    }
    private var gson = GsonBuilder().setLenient().create()

    private val imgRetrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(createOkHttpClient()).build()

    val imgNetWork : ImageInterface = imgRetrofit.create(ImageInterface::class.java)
}