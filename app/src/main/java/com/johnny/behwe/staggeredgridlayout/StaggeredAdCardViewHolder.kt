package com.johnny.behwe.staggeredgridlayout


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.johnny.behwe.R


class StaggeredAdCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//    var productImage: NetworkImageView = itemView.findViewById(R.id.product_image)
    var adTitle: TextView = itemView.findViewById(R.id.product_title)
    var adPrice: TextView = itemView.findViewById(R.id.product_price)
}
