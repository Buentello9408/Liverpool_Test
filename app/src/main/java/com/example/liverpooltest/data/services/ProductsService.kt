package com.example.liverpooltest.data.services

import com.example.liverpooltest.data.ProductsListResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {

    @GET("plp")
    suspend fun getProducts(
        @Query("search-string") busqueda: String,
        @Query("page-number") pageNumber: String
    ): Response<ProductsListResponse>
}