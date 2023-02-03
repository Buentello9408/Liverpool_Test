package com.example.liverpooltest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liverpooltest.data.ProductsListResponse
import com.example.liverpooltest.data.ProductsRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: ProductsRepository = ProductsRepository()): ViewModel() {

    private val _products = MutableLiveData<ProductsDTO>()
    val products = _products

    private val _error = MutableLiveData<ErrorDTO>()
    val error: LiveData<ErrorDTO> = _error

    fun getProducts(pageNumber: String, busqueda: String) {
        viewModelScope.launch {
            val result = repository.getProducts(pageNumber, busqueda)
            _products.postValue(result.transform(result))

        }
    }

    fun ProductsListResponse.transform(products:ProductsListResponse): ProductsDTO {
        val variantColors = products.plpResults.records
        val records = products.plpResults.records?.map { it -> RecordDTO(
            it.productDisplayName,
            it.listPrice,
            it.promoPrice,
            it.lgImage,
            it.smImage,
            it.xlImage,
            it.variantsColor?.map { itColor -> VariantsColorDTO(
                itColor.colorName,
                itColor.colorHex
            ) }
        ) }
        return ProductsDTO(records)
    }

}