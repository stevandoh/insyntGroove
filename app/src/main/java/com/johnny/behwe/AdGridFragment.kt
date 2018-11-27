package com.johnny.behwe

import android.os.Build
import android.os.Bundle

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.johnny.behwe.models.AdMDL
import com.johnny.behwe.staggeredgridlayout.StaggeredAdCardRecyclerViewAdapter

//import com.google.codelabs.mdc.kotlin.shrine.network.ProductEntry

import com.johnny.behwe.utils.NavigationIconClickListener
import com.johnny.behwe.utils.ProductGridItemDecoration
import com.vicpin.krealmextensions.queryAll
import kotlinx.android.synthetic.main.ad_grid_fragment.view.*

class AdGridFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment with the ProductGrid theme
        val view = inflater.inflate(R.layout.ad_grid_fragment, container, false)

        // Set up the tool bar
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        view.app_bar.setNavigationOnClickListener(
            NavigationIconClickListener(
                activity!!,
                view.product_grid,
                AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(context!!, R.drawable.branded_menu), // Menu open icon
                ContextCompat.getDrawable(context!!, R.drawable.close_menu)
            )
        ) // Menu close icon

        // Set up the RecyclerView
        view.recycler_view.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        view.recycler_view.layoutManager = gridLayoutManager
        val adapter = StaggeredAdCardRecyclerViewAdapter(
                AdMDL().queryAll())
        view.recycler_view.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.staggered_ad_grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.staggered_ad_grid_spacing_small)
        view.recycler_view.addItemDecoration(
            ProductGridItemDecoration(
                largePadding,
                smallPadding
            )
        )

        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.product_grid.background = context?.getDrawable(R.drawable.ad_grid_background_shape)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {
        menuInflater!!.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }
}
