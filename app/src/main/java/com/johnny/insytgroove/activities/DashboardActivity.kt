package com.johnny.insytgroove.activities

import ActivityHelper
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.johnny.insytgroove.R
import com.johnny.insytgroove.adapters.UserListAdapter
import com.johnny.insytgroove.models.AdMDL
import com.johnny.insytgroove.models.UserMDL
import com.johnny.insytgroove.models.UserProfileMDL
import com.johnny.insytgroove.utils.Constants
import com.johnny.insytgroove.utils.GenUtils
import com.johnny.insytgroove.utils.ItemClickSupport
import com.johnny.insytgroove.utils.SharedPrefManager
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.queryFirst


import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.content_dashboard.*


class DashboardActivity : AppCompatActivity() {


    private var mDrawer: Drawer? = null
    private var headerResult: AccountHeader? = null
    private var mSharedPrefManager: SharedPrefManager? = null
    private var userListAdapter: UserListAdapter? = null
    private var userMDLs: List<UserMDL>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mSharedPrefManager = SharedPrefManager(applicationContext)
//        val userProfileMDL = UserProfileMDL().queryFirst()
//        if (userProfileMDL == null) {
//            ActivityHelper.signoutDialog(this)
//        }
        headerResult = AccountHeaderBuilder()
            .withActivity(this)
//                .withHeaderBackground(R.drawable.nav_bg)
            .addProfiles(
                ProfileDrawerItem()
//                    .withName(userProfileMDL!!.user!!.fullname)
//    .withEmail(Constants.CURRENCY_SYMBOL
//    .plus( formatMoney(amountValue)))
//        .withIcon(R.drawable.nopic)
            )
            .build()

        mDrawer = DrawerBuilder()
            .withAccountHeader(headerResult!!)
            .withActivity(this)
            .withTranslucentStatusBar(false)
            .withActionBarDrawerToggle(false)
            .withToolbar(toolbar)
            .addDrawerItems(
                PrimaryDrawerItem()
                    .withName(getString(R.string.drawer_home))
                    .withSelectable(false)
                    .withIdentifier(Constants.DRAWER_HOME)
                    .withIcon(GoogleMaterial.Icon.gmd_home),
                PrimaryDrawerItem()
                    .withName("Logout")
                    .withSelectable(false)
                    .withIdentifier(Constants.DRAWER_SIGNOUT)
                    .withIcon(GoogleMaterial.Icon.gmd_exit_to_app)

            )

            .withSelectedItem(-1)
            .withOnDrawerItemClickListener { view, position, drawerItem ->
                if (drawerItem != null) {
                    var intent: Intent? = null
                    if (drawerItem.identifier == Constants.DRAWER_HOME) {

                    }
                    if (drawerItem.identifier == Constants.DRAWER_CREATE_AD) {
//                        intent = Intent(this@DashboardActivity, CreateActivity::class.java)
//                        startActivity(intent)

                    }

                    if (drawerItem.identifier == Constants.DRAWER_CONTACT_US) {

                    }

                    if (drawerItem.identifier == Constants.DRAWER_PROFILE) {
                    }

                    if (drawerItem.identifier == Constants.DRAWER_FAQS) {

                    }
                    if (drawerItem.identifier == Constants.DRAWER_SIGNOUT) {
                        ActivityHelper.signoutDialog(this)
                    }


                }

                false
            }.build()

//        fabCreateAd.setOnClickListener {
//            startActivity(Intent(this@DashboardActivity, CreateActivity::class.java))
//        }

        populateGrid()
        ItemClickSupport.addTo(rv).setOnItemClickListener { _, position, _ ->
            GenUtils.getToastMessage(applicationContext,position.toString())

            startActivity(Intent(this@DashboardActivity,PostListActivity::class.java)
                .putExtra("userId",userMDLs!![position].id))
        }


        fab.setOnClickListener { view ->
//            startActivity(Intent(this@DashboardActivity, CreateActivity::class.java))
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

   private fun populateGrid() {


        userMDLs = UserMDL().queryAll()
        if (!userMDLs!!.isEmpty()) {
            tvNoAd.visibility = View.GONE
            rv.visibility = View.VISIBLE
            userListAdapter = UserListAdapter(this, (userMDLs as MutableList<UserMDL>?)!!)
            rv.setHasFixedSize(true)
            rv.layoutManager = GridLayoutManager(this, 2)
            rv.adapter = userListAdapter
//        rv.itemAnimator = LandingAnimator()

        }



    }


}
