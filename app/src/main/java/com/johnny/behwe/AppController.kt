package com.johnny.behwe

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import io.realm.Realm
import io.realm.RealmConfiguration
import com.cloudinary.android.MediaManager
//import javax.swing.UIManager.put






class AppController : Application() {
//    val BASE_URL =
//var mp:MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
//        Fabric.with(this, Crashlytics())



        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("Realmdb.realm")
            .deleteRealmIfMigrationNeeded() // danger
            .build()
        Realm.setDefaultConfiguration(config)
        init()
        if (BuildConfig.DEBUG) {
            // do something for a debug build
            BASE_API = TEST_API
        } else {
            BASE_API = LIVE_API
        }

//        val config = MutableMap()
//        config.put("cloud_name", "myCloudName")
//        MediaManager.init(this, config)

        val configCloudinary:HashMap<String,String> = HashMap()
        configCloudinary.put("cloud_name", getString(R.string.cloudinary_key))
        MediaManager.init(this, configCloudinary)

    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    private fun init() {
        instance = this
    }


    companion object {
        // The Realm file will be located in Context.getFilesDir() with name "default.realm"

        var instance: AppController? = null
            private set
        val TAG = AppController::class.java.simpleName
        val LIVE_API = "http://209.97.186.177:3000/v1/"
        val TEST_API = "http://209.97.186.177:3000/v1/"
        var BASE_API = ""
    }
}
