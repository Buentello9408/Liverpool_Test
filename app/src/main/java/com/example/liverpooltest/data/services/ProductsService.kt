package com.example.liverpooltest.data.services

import com.example.liverpooltest.data.ProductsListResponse
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {

    @GET("plp?search-string=zapato&page-number=")
    suspend fun getProducts(
        @Query("pageNumber") pageNumber: String
    ): Response<ProductsListResponse>
}