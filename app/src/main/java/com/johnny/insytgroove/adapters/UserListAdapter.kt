package com.johnny.insytgroove.adapters

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
import com.johnny.insytgroove.R
import com.johnny.insytgroove.models.UserMDL
import java.util.*

class UserListAdapter(internal var context: Context, private var userMDLList: MutableList<UserMDL>) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.UserListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_single_card,null)
        return UserListViewHolder(itemView)
    }


    init {
        setHasStableIds(true)
    }

//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.list_single_card,null)
//        return UserListViewHolder(itemView)
//    }

    fun getItem(position: Int): UserMDL {
        return this.userMDLList.get(position)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onBindViewHolder(holder: UserListAdapter.UserListViewHolder, position: Int) {
        val obj = getItem(position)


        Glide
            .with(context)
            .load(R.drawable.default_img)
            .into(holder.itemImage);

        holder.tvTitle!!.text = obj.username


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = if (true) userMDLList.size else 0

    fun remove(position: Int) {
        userMDLList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun add(adMDL: UserMDL, position: Int) {
        userMDLList.add(adMDL)
        notifyItemInserted(position)
    }

    fun setFilter(adFiltered: List<UserMDL>) {
        userMDLList = ArrayList()
        (userMDLList as ArrayList<UserMDL>).addAll(adFiltered)
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    inner class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var data: UserMDL? = null
        var tvTitle: TextView? = null
        internal var itemImage: ImageView
//        internal var imgBtnMenu: ImageButton

        init {
            tvTitle = itemView.findViewById<View>(R.id.tvTitle) as TextView
            itemImage = itemView.findViewById<View>(R.id.itemImage) as ImageView
//            imgBtnMenu = itemView.findViewById<View>(R.id.imgBtnMenu) as ImageButton
        }
    }
}
