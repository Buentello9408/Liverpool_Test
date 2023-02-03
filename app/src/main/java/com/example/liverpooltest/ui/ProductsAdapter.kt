package com.example.liverpooltest.ui

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.liverpooltest.R
import com.google.android.material.card.MaterialCardView

class ProductsAdapter (var products: List<RecordDTO>, var context: Context): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    fun setFilteredList(products: List<RecordDTO>){
        this.products = products
        notifyDataSetChanged()
    }

    inner class ProductsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var name: TextView
        var amount: TextView
        var amountStrike: TextView
        var product: MaterialCardView
        init {
            image = itemView.findViewById(R.id.image_product)
            name = itemView.findViewById(R.id.title_product)
            amount = itemView.findViewById(R.id.amount_product)
            amountStrike = itemView.findViewById(R.id.amount_product_strike)
            product = itemView.findViewById(R.id.product)
        }
        fun initFrom(product:RecordDTO, context: Context) {
            Glide.with(context).load(product.lgImage).placeholder(R.drawable.ic_baseline_broken_image_24).error(R.drawable.ic_baseline_broken_image_24).into(image)
            name.text = product.productDisplayName
            if (product.promoPrice.toInt() > 0) {
                amount.setTextAppearance(context, R.style.OfPriceRedBoldLeft16)
                amountStrike.visibility = View.VISIBLE
                amount.text = "$ ${product.promoPrice.toString()}"
                amountStrike.text = "$ ${product.listPrice.toString()}"
                amountStrike.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                amount.setTextAppearance(context, R.style.OfPriceBlackBoldLeft16)
                amountStrike.visibility = View.GONE
                amount.text = "$ ${product.listPrice}"
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.initFrom(products[position], context)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}