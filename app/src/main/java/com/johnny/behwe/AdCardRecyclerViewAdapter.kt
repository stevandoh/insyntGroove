package com.johnny.behwe


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johnny.behwe.models.AdMDL


/**
 * Adapter used to show a simple grid of ads.
 */
class AdCardRecyclerViewAdapter internal constructor(private val adList: List<AdMDL>)
    : RecyclerView.Adapter<AdCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.ad_card, parent, false)
        return AdCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: AdCardViewHolder, position: Int) {
        if (position < adList.size) {
            val ad = adList[position]
            holder.adTitle.text = ad.title
            holder.adPrice.text = ad.title
//            ImageRequester.setImageFromUrl(holder.adImage, ad.url)
        }
    }

    override fun getItemCount(): Int {
        return adList.size
    }
}
