package com.android.mycatdog.data

data class ReceiveItems(
    val id : String,
    val url: String,
    val breeds : List<BreedsItem>,
    val width: Int,
    val height: Int
)

data class BreedsItem(
    val id : String,
    val name : String,
    val cfa_url : String,
    val temperament : String,
    val origin : String,
    val description : String,
)
