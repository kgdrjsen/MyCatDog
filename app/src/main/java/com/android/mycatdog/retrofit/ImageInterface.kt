package com.android.mycatdog.retrofit

import com.android.mycatdog.apidata.CatModel
import com.android.mycatdog.apidata.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageInterface {
    @GET("images/search")
    suspend fun imageRandomSearch(
        @Header("x-api-key") apiKey : String = Constants.API_KEY,
        @Query("limit") limit : Int = 10
    ) : CatModel

    @GET("images/search")
    suspend fun imageBreedsSearch(
        @Header("x-api-key") apiKey : String = Constants.API_KEY,
        @Query("limit") limit : Int,
        @Query("breed_ids") breeds : String
    ) : CatModel
}