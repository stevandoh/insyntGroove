//.helpers
//
//import android.content.Context
//import android.support.v4.content.ContextCompat
//import android.support.v4.view.ViewPager
//import android.util.TypedValue
//import android.view.View
//import android.widget.Button
//import com.sovavyw8.android.kyc.R
//import com.sovavyw8.android.kyc.adapters.MyCustomPagerAdapter
//import com.sovavyw8.android.kyc.utils.wizard.pages.Page
//
//open class FormHelper{
//
//    private fun updateBottomBar(context: Context,
//                                mNextButton: Button,
//                                mPrevButton:Button,
//                                mPager: ViewPager,
//                                mCurrentPageSequence: List<Page>,
//                                mEditingAfterReview: Boolean ,
//                                mPagerAdapter: MyCustomPagerAdapter
//    ) {
//
//        val position = mPager!!.currentItem
//        //        GenUtils.getToastMessage(getApplicationContext(),String.valueOf(position));
//        if (position == mCurrentPageSequence!!.size) {
//            mNextButton!!.setText(R.string.finish)
//            mNextButton!!.setBackgroundResource(R.drawable.finish_background)
//            mNextButton!!.setBackgroundColor(ContextCompat.getColor (this, R.color.accent))
//            mNextButton!!.setTextColor(ContextCompat.getColor(context, R.color.md_white_1000))
//            //            mNextButton.setTextAppearance(this, R.style.TextAppearanceFinish);
//        } else {
//            mNextButton!!.setText(
//                if (mEditingAfterReview)
//                    R.string.review
//                else
//                    R.string.next
//            )
//            mNextButton!!.setBackgroundResource(R.drawable.selectable_item_background)
//            val v = TypedValue()
//            context.theme.resolveAttribute(android.R.attr.textAppearanceMedium, v, true)
//
//            mNextButton!!.setTextAppearance(context, v.resourceId)
//            mNextButton!!.isEnabled = position != mPagerAdapter!!.cutOffPage
//        }
//
//        mPrevButton!!.visibility = if (position <= 0) View.INVISIBLE else View.VISIBLE
//    }
//
////    private fun recalculateCutOffPage(): Boolean {
////        // Cut off the pager adapter at first required page that isn't completed
////        var cutOffPage = mCurrentPageSequence!!.size + 1
////        for (i in mCurrentPageSequence!!.indices) {
////            val page = mCurrentPageSequence!![i]
////            if (page.isRequired && !page.isCompleted) {
////                cutOffPage = i
////                break
////            }
////        }
////
////        if (mPagerAdapter!!.cutOffPage != cutOffPage) {
////            mPagerAdapter!!.cutOffPage = cutOffPage
////            return true
////        }
////
////        return false
////    }
//
//}
