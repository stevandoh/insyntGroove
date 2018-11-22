

import Constants.ID_TOKEN
import Constants.IS_LOGGED_IN
import android.content.Context
import android.content.SharedPreferences



class SharedPrefManager(internal var mContext: Context) {
    internal var sharedPreferences: SharedPreferences
    // shared pref mode
    internal var PRIVATE_MODE = 0
    internal var editor: SharedPreferences.Editor

    //mContext = context;
    val isLogged_IN: Boolean
        get() {
            sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
            return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
        }

    //mContext = context;
    val userToken: String
        get() {
            sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
            return sharedPreferences.getString(ID_TOKEN, "")
        }

    init {
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = sharedPreferences.edit()
    }


    fun saveIsLoggedIn(context: Context, isLoggedIn: Boolean?) {
        mContext = context
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn!!)
        editor.commit()
    }

    fun saveToken(context: Context, toke: String) {
        mContext = context
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        editor.putString(ID_TOKEN, toke)
        editor.commit()
    }

    fun clear() {
        editor.clear()
        editor.apply()
    }

    companion object {
        // Shared preferences file name
        private val PREF_NAME = "sessionPref"
    }
}
