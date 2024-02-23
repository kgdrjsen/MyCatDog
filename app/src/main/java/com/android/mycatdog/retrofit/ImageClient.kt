package com.android.mycatdog.retrofit

import com.android.mycatdog.data.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

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
        .addConverterFactory(ScalarsConverterFactory.create()) //배열로 받는걸 string으로
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(createOkHttpClient()).build()

    val imgNetWork : ImageInterface = imgRetrofit.create(ImageInterface::class.java)
}