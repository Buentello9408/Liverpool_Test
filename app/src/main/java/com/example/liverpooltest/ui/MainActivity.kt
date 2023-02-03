package com.example.liverpooltest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liverpooltest.R
import com.example.liverpooltest.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.List

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var mainActivityViewModel: MainActivityViewModel
    var productsAdapter: ProductsAdapter? = null
    var recordsDto: List<RecordDTO>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        productRecyclerView = binding.productsRecyclerView
        searchView = binding.searchView
        mainActivityViewModel.getProducts("1", "")
        observeProducts()
    }

    private fun initRecycler(productsDTO: ProductsDTO) {
        productRecyclerView.setHasFixedSize(true)
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productsDTO.records?.let {
            recordsDto = it
            productsAdapter = ProductsAdapter(it, this)
            productRecyclerView.adapter = productsAdapter
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    mainActivityViewModel.getProducts("1", query?:"")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }



    }

    private fun filterList(newText: String?) {
        if (newText.isNullOrBlank()) {
            val filteredList = ArrayList<RecordDTO>()
            for ( i in recordsDto!!){
                if (i.productDisplayName.lowercase(Locale.ROOT).contains(newText?:"")) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "articulo no encontrado", Toast.LENGTH_SHORT)
            }else {
                productsAdapter?.setFilteredList(filteredList)
            }
        }
    }

    private fun observeProducts() {
        mainActivityViewModel.products.observe(this) {
            initRecycler(it)
        }
    }

    private fun observeError() {
        mainActivityViewModel.error.observe(this) { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }
}