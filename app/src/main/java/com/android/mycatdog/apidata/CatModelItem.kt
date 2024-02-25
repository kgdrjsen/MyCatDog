package com.android.mycatdog.apidata


import com.google.gson.annotations.SerializedName

data class CatModelItem(
    @SerializedName("breeds")
    val breeds: List<BreedsItem>,
    @SerializedName("favourite")
    val favourite: Favourite,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class BreedsItem(
    val id : String?,
    val name : String?,
    val cfa_url : String?,
    val temperament : String?,
    val origin : String?,
    val description : String?,
)