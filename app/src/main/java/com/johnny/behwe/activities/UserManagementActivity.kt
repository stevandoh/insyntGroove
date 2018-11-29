package com.johnny.behwe.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.johnny.behwe.R
import com.johnny.behwe.models.UserProfileMDL
import com.vicpin.krealmextensions.queryFirst

import kotlinx.android.synthetic.main.activity_user_management.*
import kotlinx.android.synthetic.main.content_user_management.*

class UserManagementActivity : AppCompatActivity() {

    private var mUserProfile :UserProfileMDL? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_management)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        mUserProfile = UserProfileMDL().queryFirst()
        if (mUserProfile !=null){
            displayProfileInfo(mUserProfile!!)
        }

        btn_wallet.setOnClickListener {
            startActivity(Intent(this@UserManagementActivity, WalletActivity::class.java))
        }

        btn_change_pwd.setOnClickListener {
            startActivity(
                Intent(this@UserManagementActivity, ResetPasswordActivity::class.java)
                    .putExtra("fromAccount",true)
            )
        }
    }


    private fun displayProfileInfo(userProfile: UserProfileMDL){
        tv_username.text = userProfile.user!!.fullname
        tv_name.text = "${userProfile.user!!.firstname} ${userProfile.user!!.lastname}"
        if (userProfile.user!!.status ==1){
            tv_account_status.text = "active"
        }else{
            tv_account_status.text = "inactive"
        }
//        tv_phone.text = userProfile.user!!.phone!!.number
//        tv_city.text = userProfile.user!!.city

    }


//    override fun onBackPressed() {
//        super.onBackPressed()
//        finish()
//    }

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
