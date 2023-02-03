package com.example.liverpooltest.data

import com.example.liverpooltest.data.services.ProductsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRepository {
    suspend fun getProducts(pageNumber: String): ProductsListResponse {
        return withContext(Dispatchers.IO) {
            val plpResponseEmpty = PlpResultsResponse(emptyList())
            try {
                val response = RestClients.getRestEngine().create(ProductsService::class.java)
                    .getProducts(pageNumber)
                val getResponse: ProductsListResponse = if (response.isSuccessful) {
                    response.body() ?: ProductsListResponse(response.code(),plpResponseEmpty, "No Body")
                } else {
                    if (response.errorBody() != null) {
                        ProductsListResponse(response.code(),  plpResponseEmpty, response.errorBody()!!.string())
                    } else {
                        ProductsListResponse(response.code(), plpResponseEmpty, "no Body")
                    }
                }
                getResponse
            } catch (e : Exception) {
                ProductsListResponse(500, plpResponseEmpty, e.localizedMessage!!)
            }
        }
    }
}