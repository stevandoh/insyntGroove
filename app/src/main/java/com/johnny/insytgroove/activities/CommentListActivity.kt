package com.johnny.insytgroove.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import com.johnny.insytgroove.R
import com.johnny.insytgroove.adapters.CommentListAdapter
import com.johnny.insytgroove.adapters.PostListAdapter
import com.johnny.insytgroove.models.CommentMDL
import com.johnny.insytgroove.models.PostMDL
import com.johnny.insytgroove.models.UserMDL
import com.johnny.insytgroove.utils.GenUtils
import com.johnny.insytgroove.utils.ServerUtils
import com.johnny.insytgroove.utils.SharedPrefManager
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.saveAll
import io.realm.RealmList


import kotlinx.android.synthetic.main.activity_comment_list.*
import kotlinx.android.synthetic.main.content_comment_list.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentListActivity : AppCompatActivity() {

    private var mSharedPrefManager: SharedPrefManager? = null
    private var commentListAdapter: CommentListAdapter? = null
    private var commentMDLs: List<CommentMDL>? = null
    private var postId = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_list)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }


        postId = intent.getIntExtra("postId",0)
//        fab.setOnClickListener { view ->
//
//
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        populateList()
    }


    private fun populateList() {


//        val events = Event().query { equalTo("id",1) } //Or query<Event> { ... }
        if (postId != 0) {
            commentMDLs = CommentMDL().query { equalTo("postId", postId) }


            if (!commentMDLs!!.isEmpty()) {
                tvNoAd.visibility = View.GONE
                rv.visibility = View.VISIBLE

                commentListAdapter = CommentListAdapter(this, (commentMDLs as MutableList<CommentMDL>?)!!)
                rv.setHasFixedSize(true)
                GenUtils.getToastMessage(applicationContext, commentMDLs!!.size.toString())
                rv.layoutManager = LinearLayoutManager(this)
                rv.adapter = commentListAdapter
//        rv.itemAnimator = LandingAnimator()

            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
