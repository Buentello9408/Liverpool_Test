package com.example.liverpooltest.ui

data class ProductsDTO(
    val records: List<RecordDTO>?
)

data class RecordDTO(
    val productDisplayName: String,
    val listPrice: Float,
    val promoPrice: Float,
    val lgImage: String,
    val smImage: String,
    val xlImage: String,
    val variantsColor: List<VariantsColorDTO>?
)

data class VariantsColorDTO(
    val colorName: String,
    val colorHex: String
)

data class ErrorDTO(
    val code: Int?,
    val message: String?
)
