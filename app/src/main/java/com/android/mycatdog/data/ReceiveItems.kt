package com.android.mycatdog.data

data class Receive(
    val item : List<ReceiveItems>
)

data class ReceiveItems(
    val id : String?,
    val url: String?,
    val breeds : List<BreedsItem>,
    val categories : List<CategoryList>,
    val width: Int?,
    val height: Int?
)

data class BreedsItem(
    val id : String?,
    val name : String?,
    val cfa_url : String?,
    val temperament : String?,
    val origin : String?,
    val description : String?,
)

data class CategoryList (
    val id : String?,
    val name : String?
)
