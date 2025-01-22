package com.example.peerpicks.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.peerpicks.R
import com.example.peerpicks.model.ProductModel

class ProductAdapter(
    val context: Context,
    var data: ArrayList<ProductModel>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val pName: TextView = itemView.findViewById(R.id.displayName)
        val pRate: TextView = itemView.findViewById(R.id.displayRating)
        val pDesc: TextView = itemView.findViewById(R.id.displayDesc)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView : View = LayoutInflater.from(context)
            .inflate(R.layout.sample_product,parent,false)
        return ProductViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.pName.text = data[position].productName
        holder.pRate.text= data[position].productRating.toString()
        holder.pDesc.text = data[position].productDesc
    }
    fun updateData(products: List<ProductModel>){
        data.clear()
        data.addAll(products)
        notifyDataSetChanged()
    }
}