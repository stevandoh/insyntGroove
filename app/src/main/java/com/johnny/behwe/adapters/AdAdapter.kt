package com.johnny.behwe.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.johnny.behwe.R
import com.johnny.behwe.models.AdMDL
import com.vicpin.krealmextensions.queryFirst
import java.util.ArrayList

open class AdListAdapter(internal var context: Context, internal var adMDLList: MutableList<AdMDL>)
    : RecyclerView.Adapter<AdListAdapter.AdListViewHolder>() {

    init {
        setHasStableIds(true)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_single_card, null)
        return AdListViewHolder(itemView)
    }

    fun getItem(position: Int): AdMDL {
        return this.adMDLList.get(position)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onBindViewHolder(holder: AdListViewHolder, position: Int) {
        val obj = getItem(position)


        Glide
            .with(context)
            .load(obj.mediaPath)
            .into(holder.itemImage);

        holder.tvTitle!!.text = obj.title

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = if (true) adMDLList.size else 0

    fun remove(position: Int) {
        adMDLList.removeAt(position)
        notifyItemRemoved(position)
    }
    fun add(adMDL: AdMDL, position: Int) {
        adMDLList.add(adMDL)
        notifyItemInserted(position)
    }

    fun setFilter(adFiltered: List<AdMDL>) {
        adMDLList = ArrayList()
        (adMDLList as ArrayList<AdMDL>).addAll(adFiltered)
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    inner class AdListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var data: AdMDL? = null
        var tvTitle: TextView? = null
        internal var itemImage: ImageView

        init {
            tvTitle = itemView.findViewById<View>(R.id.tvTitle) as TextView
            itemImage = itemView.findViewById<View>(R.id.itemImage) as ImageView
        }
    }
}



