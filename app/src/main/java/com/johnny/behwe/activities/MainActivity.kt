package com.johnny.behwe.activities

import ActivityHelper
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.johnny.behwe.R
import com.johnny.behwe.models.UserProfileMDL
import com.johnny.behwe.utils.Constants
import com.johnny.behwe.utils.SharedPrefManager
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.vicpin.krealmextensions.queryFirst
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager
import com.johnny.behwe.adapters.AdListAdapter
import com.johnny.behwe.models.AdMDL
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.querySorted
import io.realm.Sort
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    internal var mDrawer: Drawer? = null
    internal var headerResult: AccountHeader? = null
    internal var mSharedPrefManager: SharedPrefManager? = null
    private var adListAdapter: AdListAdapter? = null
    private var adMDLs: List<AdMDL>? = null
    private var adCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mSharedPrefManager = SharedPrefManager(applicationContext)
        val userProfileMDL = UserProfileMDL().queryFirst()
        if (userProfileMDL == null) {
            ActivityHelper.signoutDialog(this)
        }

//        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar

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
                    .withName(getString(R.string.drawer_create_ad))
                    .withSelectable(false)
                    .withIdentifier(Constants.DRAWER_CREATE_AD)
                    .withIcon(GoogleMaterial.Icon.gmd_games),
                PrimaryDrawerItem()
                    .withName("Profile")
                    .withSelectable(false)
                    .withIdentifier(Constants.DRAWER_PROFILE)
                    .withIcon(GoogleMaterial.Icon.gmd_person_pin_circle),
                DividerDrawerItem(),
                PrimaryDrawerItem()
                    .withName("FAQs")
                    .withSelectable(false)
                    .withIdentifier(Constants.DRAWER_FAQS)
                    .withIcon(GoogleMaterial.Icon.gmd_question_answer),
//                PrimaryDrawerItem()
//                    .withName("About")
//                    .withSelectable(false)
//                    .withIdentifier(Constants.DRAWER_CONTACT_US)
//                    .withIcon(GoogleMaterial.Icon.gmd_contact_phone),
                PrimaryDrawerItem()
                    .withName("Terms and conditions")
                    .withSelectable(false)
                    .withIdentifier(Constants.DRAWER_T_AND_C)
                    .withIcon(GoogleMaterial.Icon.gmd_info),
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
                        intent = Intent(this@MainActivity, CreateAdActivity::class.java)
                        startActivity(intent)

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

        fabCreateAd.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateAdActivity::class.java))
        }

        populateGrid()


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

    fun populateGrid() {


        adMDLs =  AdMDL().queryAll()
        if(!adMDLs!!.isEmpty()){
            tvNoAd.visibility = View.GONE
            rv.visibility = View.VISIBLE
            adListAdapter = AdListAdapter(this, (adMDLs as MutableList<AdMDL>?)!!)
            rv.setHasFixedSize(true)
            rv.layoutManager = GridLayoutManager(this, 3)
            rv.adapter = adListAdapter
//        rv.itemAnimator = LandingAnimator()

        }

    }


}
