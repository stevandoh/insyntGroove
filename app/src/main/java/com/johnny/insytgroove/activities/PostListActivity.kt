package com.johnny.insytgroove.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.google.android.material.snackbar.Snackbar
import com.johnny.insytgroove.R
import com.johnny.insytgroove.adapters.PostListAdapter
import com.johnny.insytgroove.models.PostMDL
import com.johnny.insytgroove.utils.GenUtils
import com.johnny.insytgroove.utils.ItemClickSupport
import com.johnny.insytgroove.utils.SharedPrefManager
import com.vicpin.krealmextensions.query
import kotlinx.android.synthetic.main.activity_post_list.*
import kotlinx.android.synthetic.main.content_dashboard.*

class PostListActivity : AppCompatActivity() {
    private var mSharedPrefManager: SharedPrefManager? = null
    private var postListAdapter: PostListAdapter? = null
    private var postsMDLs: List<PostMDL>? = null
    private var userId = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        userId = intent.getIntExtra("userId", 0)


        populateList()
        ItemClickSupport.addTo(rv).setOnItemClickListener { _, position, _ ->
            GenUtils.getToastMessage(applicationContext, position.toString())

            MaterialDialog(this).show {

                listItems(R.array.socialNetworks)
            }
            displayDialog(position)
        }

    }

    private fun displayDialog(position: Int) {
        MaterialDialog(this@PostListActivity).show {
            title(text = "Add comment")
            message(text = "Do you want to add a comment?")
            positiveButton(R.string.agree) { dialog ->
                startActivity(
                    Intent(this@PostListActivity, CreateCommentActivity::class.java)
                        .putExtra("postId", postsMDLs!![position].id)
                )
            }

        }
    }


    private fun populateList() {


//        val events = Event().query { equalTo("id",1) } //Or query<Event> { ... }
        if (userId != 0) {
            postsMDLs = PostMDL().query { equalTo("userId", userId) }


            if (!postsMDLs!!.isEmpty()) {
                tvNoAd.visibility = View.GONE
                rv.visibility = View.VISIBLE

                postListAdapter = PostListAdapter(this, (postsMDLs as MutableList<PostMDL>?)!!)
                rv.setHasFixedSize(true)
                GenUtils.getToastMessage(applicationContext, postsMDLs!!.size.toString())
                rv.layoutManager = LinearLayoutManager(this)
                rv.adapter = postListAdapter
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
