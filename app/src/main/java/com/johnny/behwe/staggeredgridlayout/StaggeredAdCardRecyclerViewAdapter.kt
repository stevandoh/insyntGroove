package com.johnny.behwe.staggeredgridlayout


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johnny.behwe.R
import com.johnny.behwe.models.AdMDL

/**
 * Adapter used to show an asymmetric grid of ads, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
open class StaggeredAdCardRecyclerViewAdapter(private val adList: List<AdMDL>?) : RecyclerView.Adapter<StaggeredAdCardViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position % 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggeredAdCardViewHolder {
        var layoutId = R.layout.ad_staggered_ad_card_first
        if (viewType == 1) {
            layoutId = R.layout.ad_staggered_ad_card_second
        } else if (viewType == 2) {
            layoutId = R.layout.ad_staggered_ad_card_third
        }

        val layoutView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return StaggeredAdCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: StaggeredAdCardViewHolder, position: Int) {
        if (adList != null && position < adList.size) {
            val ad = adList[position]
            holder.adTitle.text = ad.title
            holder.adPrice.text = ad.title
//            ImageRequester.setImageFromUrl(holder.adImage, ad.url)
        }
    }

    override fun getItemCount(): Int {
        return adList?.size ?: 0
    }
}
