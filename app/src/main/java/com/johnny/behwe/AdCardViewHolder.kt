package com.johnny.behwe

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class AdCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//    var adImage: NetworkImageView = itemView.findViewById(R.id.ad_image)
    var adTitle: TextView = itemView.findViewById(R.id.ad_title)
    var adPrice: TextView = itemView.findViewById(R.id.ad_price)
}
