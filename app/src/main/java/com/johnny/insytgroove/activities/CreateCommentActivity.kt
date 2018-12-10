package com.johnny.insytgroove.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.snackbar.Snackbar
import com.johnny.insytgroove.R
import com.johnny.insytgroove.models.CommentMDL
import com.johnny.insytgroove.utils.FormattingUtils
import com.johnny.insytgroove.utils.GenUtils
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.activity_create_comment.*
import kotlinx.android.synthetic.main.content_create_comment.*

class CreateCommentActivity : AppCompatActivity() {

    private var postId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_comment)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }


//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        postId = intent.getIntExtra("postId", 0)

        btnSubmit.setOnClickListener{
            saveComment()
        }


    }


    private fun saveComment() {

        val usernameError = GenUtils.isEmpty(
            tiUsername.editText
            , tiUsername
            , "Email is required"
        )
        val commentError = GenUtils.isEmpty(
            tiComment.editText
            , tiComment
            , "A comment is required"
        )
        val titleError = GenUtils.isEmpty(
            tiTitle.editText
            , tiTitle
            , "Tile is required"
        )


        if (!(usernameError && commentError && titleError)) {
            tiUsername.error = "Email field cannot be empty"
            tiComment.error = "Comment field cannot be empty"
            tiTitle.error = "Title field cannot be empty"
            GenUtils.getToastMessage(applicationContext, "None of the fields must be empty")
        }
        else if( FormattingUtils.validateEmail(tiUsername.editText!!.text.toString().trim())){
            tiUsername.error = "Email is not valid"
        }
        else {
            val id = (501 until 10000).random()

            CommentMDL(
                id
                , postId
                , tiTitle.editText!!.text.toString()
                , tiUsername.editText!!.text.toString()
                , tiComment.editText!!.text.toString()
            )
                .save()

            displayDialog()


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
    private fun displayDialog() {
        MaterialDialog(this@CreateCommentActivity).show {
            title(text = "Comment Saved")
            message(text = "Your comment has been successfully saved")
            positiveButton(R.string.agree) { _ ->
                startActivity(
                    Intent(this@CreateCommentActivity, DashboardActivity::class.java)
                )
                finish()
            }

        }
    }
}
