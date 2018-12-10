package com.johnny.insytgroove.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.johnny.insytgroove.R
import com.johnny.insytgroove.adapters.PostListAdapter
import com.johnny.insytgroove.adapters.UserListAdapter
import com.johnny.insytgroove.models.PostMDL
import com.johnny.insytgroove.models.UserMDL
import com.johnny.insytgroove.utils.GenUtils
import com.johnny.insytgroove.utils.ItemClickSupport
import com.johnny.insytgroove.utils.SharedPrefManager
import com.vicpin.krealmextensions.queryAll
import kotlinx.android.synthetic.main.activity_post_list.*
import kotlinx.android.synthetic.main.content_dashboard.*

class PostListActivity : AppCompatActivity() {
    private var mSharedPrefManager: SharedPrefManager? = null
    private var postListAdapter: PostListAdapter? = null
    private var postsMDLs: List<PostMDL>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        populateList()
        ItemClickSupport.addTo(rv).setOnItemClickListener { _, position, _ ->
          GenUtils.getToastMessage(applicationContext,position.toString())
        }

    }


    private fun populateList() {


        postsMDLs = PostMDL().queryAll()
        if (!postsMDLs!!.isEmpty()) {
            tvNoAd.visibility = View.GONE
            rv.visibility = View.VISIBLE
            postListAdapter = PostListAdapter(this, (postsMDLs as MutableList<PostMDL>?)!!)
            rv.setHasFixedSize(true)
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = postListAdapter
//        rv.itemAnimator = LandingAnimator()

        }

    }
}
