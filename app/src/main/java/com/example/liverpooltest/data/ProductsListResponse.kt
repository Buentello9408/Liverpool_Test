package com.example.liverpooltest.data

data class ProductsListResponse(
    val statusCode: Int,
    val plpResults: PlpResultsResponse,
    val message: String
)

data class PlpResultsResponse(
    val records: List<Records>?,
)

data class Records(
    val productDisplayName: String,
    val listPrice: String,
    val promoPrice: String,
    val lgImage: String,
    val smImage: String,
    val xlImage: String,
    val variantsColor: List<VariantsColor>
)

data class VariantsColor(
    val colorName: String,
    val colorHex: String
)
