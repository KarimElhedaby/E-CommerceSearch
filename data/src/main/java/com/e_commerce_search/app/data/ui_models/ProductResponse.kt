package com.e_commerce_search.app.data.ui_models



data class ProductResponse(
    val category: String,
    val id: Int,
    val image: String,
    val name: String,
    val price: Double,
    val rate: Float
)