

import android.app.Activity
import android.app.AlertDialog
import com.afollestad.materialdialogs.MaterialDialog
import com.johnny.insytgroove.R

class ActivityHelper {


    companion object {


        fun cancelRegistration(activity: Activity) {

            val dialog = AlertDialog.Builder(activity)

            dialog.setTitle(activity.getString(R.string.confirm_title))
            dialog.setMessage("Are you sure you want to cancel registration?")

            dialog.setCancelable(false)
                .setPositiveButton(
                    "Yes"
                ) { dialog, id -> activity.finish() }
                .setNegativeButton(
                    "No"
                ) { dialog, id -> }
            val alert = dialog.create()
            alert.show()
        }


        fun signoutDialog(activity: Activity) {
            MaterialDialog(activity)
                .title(R.string.confirm_logout)
                .message(R.string.logout_msg)
                .negativeButton(R.string.btn_cancel)
                .positiveButton(R.string.btn_ok){ dialog ->
//                    signout(activity)
                }
                .show()



        }
//
//        fun signout(activity: Activity) {
//            val mSharedPrefManager: SharedPrefManager? = SharedPrefManager(activity.applicationContext)
//            mSharedPrefManager!!.clear()
//            UserProfileMDL().deleteAll()
//            activity.startActivity(
//                Intent(
//                     activity,
//                    SigninActivity::class.java
//                )
//            )
//            activity.finish()
//        }


    }


}
