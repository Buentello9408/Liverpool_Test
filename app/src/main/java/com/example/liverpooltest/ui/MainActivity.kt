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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var mainActivityViewModel: MainActivityViewModel
    var productsAdapter: ProductsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        productRecyclerView = binding.productsRecyclerView
        searchView = binding.searchView
        mainActivityViewModel.getProducts("1")
        observeProducts()
    }

    private fun initRecycler(productsDTO: ProductsDTO) {
        productRecyclerView.setHasFixedSize(true)
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productsDTO.records?.let {
            productsAdapter = ProductsAdapter(it, this)
            productRecyclerView.adapter = productsAdapter
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