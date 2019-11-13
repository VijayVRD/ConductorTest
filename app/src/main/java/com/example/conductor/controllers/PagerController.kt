package com.example.conductor.controllers

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import com.example.conductor.R
import com.example.conductor.controllers.base.BaseController
import com.example.conductor.databinding.ControllerPagerBinding
import java.util.*


class PagerController : BaseController<ControllerPagerBinding>() {
    private val pagerAdapter: RouterPagerAdapter

    private val PAGE_COLORS = intArrayOf(
        Color.GRAY,
        Color.RED,
        Color.GREEN,
        Color.DKGRAY,
        Color.YELLOW
    )


    init {
        pagerAdapter = object : RouterPagerAdapter(this) {
            override fun getCount(): Int = PAGE_COLORS.size


            override fun configureRouter(router: Router, position: Int) {
                if (!router.hasRootController()) {
                    val page = ChildController(
                        String.format(
                            Locale.getDefault(),
                            "Child #%d (Swipe to see more)",
                            position
                        ), PAGE_COLORS[position]
                    )
                    router.setRoot(RouterTransaction.with(page))
                }
            }

            override fun getPageTitle(position: Int): CharSequence {
                return "Page $position"
            }
        }
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): ControllerPagerBinding {
        return DataBindingUtil.inflate(inflater,
            R.layout.controller_pager, container, false)
    }

    override fun onViewBound(view: View) {
        super.onViewBound(view)
        with(binding) {
            viewPager.adapter = pagerAdapter
            tabLayout!!.setupWithViewPager(viewPager)
        }
    }

    override fun onDestroyView(view: View) {
        with(binding) {
            if (!activity!!.isChangingConfigurations) {
                viewPager!!.adapter = null
            }
            tabLayout!!.setupWithViewPager(null)
        }
        super.onDestroyView(view)
    }

}
