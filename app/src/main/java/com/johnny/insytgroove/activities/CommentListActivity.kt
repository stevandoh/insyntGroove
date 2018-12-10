package com.johnny.insytgroove.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.johnny.insytgroove.R
import com.johnny.insytgroove.models.UserMDL
import com.johnny.insytgroove.utils.GenUtils
import com.johnny.insytgroove.utils.ServerUtils
import com.vicpin.krealmextensions.saveAll
import io.realm.RealmList


import kotlinx.android.synthetic.main.activity_comment_list.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }



}
