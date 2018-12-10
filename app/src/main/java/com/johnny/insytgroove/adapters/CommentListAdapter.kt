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
import com.johnny.insytgroove.models.CommentMDL
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class CommentListAdapter(internal var context: Context, private var commentMDLList: MutableList<CommentMDL>) :
    RecyclerView.Adapter<CommentListAdapter.CommentListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListAdapter.CommentListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_row, parent, false)
        return CommentListViewHolder(itemView)
    }


    init {
        setHasStableIds(true)
    }

//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.list_single_card,null)
//        return CommentListViewHolder(itemView)
//    }

    fun getItem(position: Int): CommentMDL {
        return this.commentMDLList.get(position)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onBindViewHolder(holder: CommentListAdapter.CommentListViewHolder, position: Int) {
        val obj = getItem(position)


        Glide
            .with(context)
            .load(R.drawable.comments)
            .into(holder.itemImage!!);

        holder.tvTitle!!.text = obj.name
        holder.tvDescription!!.text = obj.body
        holder.tvEmail!!.text = obj.email


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = if (true) commentMDLList.size else 0

    fun remove(position: Int) {
        commentMDLList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun add(adMDL: CommentMDL, position: Int) {
        commentMDLList.add(adMDL)
        notifyItemInserted(position)
    }

    fun setFilter(adFiltered: List<CommentMDL>) {
        commentMDLList = ArrayList()
        (commentMDLList as ArrayList<CommentMDL>).addAll(adFiltered)
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    inner class CommentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var data: CommentMDL? = null
        var tvTitle: TextView? = null
        var tvDescription: TextView? = null
        var tvEmail: TextView? = null
        var itemImage: CircleImageView ? = null
//        internal var imgBtnMenu: ImageButton

        init {
            tvTitle = itemView.findViewById<View>(R.id.tv_comment_title) as TextView
            tvDescription = itemView.findViewById<View>(R.id.tv_comment_description) as TextView
            tvEmail = itemView.findViewById<View>(R.id.tv_comment_email) as TextView
            itemImage = itemView.findViewById<View>(R.id.img_comment) as CircleImageView
//            imgBtnMenu = itemView.findViewById<View>(R.id.imgBtnMenu) as ImageButton
        }
    }
}
