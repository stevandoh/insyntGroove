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
import com.johnny.insytgroove.models.PostMDL
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class PostListAdapter(internal var context: Context, private var postMDLList: MutableList<PostMDL>) :
    RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.PostListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_row,null)
        return PostListViewHolder(itemView)
    }


    init {
        setHasStableIds(true)
    }

//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.list_single_card,null)
//        return PostListViewHolder(itemView)
//    }

    fun getItem(position: Int): PostMDL {
        return this.postMDLList.get(position)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onBindViewHolder(holder: PostListAdapter.PostListViewHolder, position: Int) {
        val obj = getItem(position)


        Glide
            .with(context)
            .load(R.drawable.default_img)
            .into(holder.itemImage!!);

        holder.tvTitle!!.text = obj.title
        holder.tvDescription!!.text = obj.body


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = if (true) postMDLList.size else 0

    fun remove(position: Int) {
        postMDLList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun add(adMDL: PostMDL, position: Int) {
        postMDLList.add(adMDL)
        notifyItemInserted(position)
    }

    fun setFilter(adFiltered: List<PostMDL>) {
        postMDLList = ArrayList()
        (postMDLList as ArrayList<PostMDL>).addAll(adFiltered)
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    inner class PostListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var data: PostMDL? = null
        var tvTitle: TextView? = null
        var tvDescription: TextView? = null
        var itemImage: CircleImageView ? = null
//        internal var imgBtnMenu: ImageButton

        init {
            tvTitle = itemView.findViewById<View>(R.id.tv_post_title) as TextView
            tvDescription = itemView.findViewById<View>(R.id.tv_post_description) as TextView
            itemImage = itemView.findViewById<View>(R.id.img_post) as CircleImageView
//            imgBtnMenu = itemView.findViewById<View>(R.id.imgBtnMenu) as ImageButton
        }
    }
}
