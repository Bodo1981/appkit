package com.christianbahl.appkit.core.kotlin.activity

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.astuetz.PagerSlidingTabStrip
import com.christianbahl.appkit.core.kotlin.R
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView

/**
 * An activity which uses the Model-View-Presenter architecture. It also adds a
 * [android.support.v7.widget.Toolbar] on top and has a [ViewPager] with [PagerSlidingTabStrip].
 *
 * The layout has to contain a view with id `R.layout.conten_view` which must be of
 * type [ViewPager]. You also have to provide a view with id `R.layout.tabs` of
 * type [PagerSlidingTabStrip].
 *
 * The standard layout implements all necessary views. You can override the default layout in
 * [.getLayoutRes]. But be careful, you have to provide the necessary views!
 *
 * There a two functions to customize the [ViewPager]
 *
 *  * getPageMargin(): sets the margin between the pages
 *  * getViewPagerDividerDrawable(): sets divider [android.graphics.drawable.Drawable] between the pages
 *
 * @author Christian Bahl
 */
public abstract class CBActivityMvpToolbarTabs<A : PagerAdapter, D, V : MvpLceView<D>, P : MvpPresenter<V>> : CBActivityMvpToolbar<ViewPager, D, V, P>() {

    protected var tabs: PagerSlidingTabStrip? = null
    protected var adapter: A? = null

    override fun onMvpViewCreated() {
        tabs = findViewById(R.id.tabs)!! as PagerSlidingTabStrip
        adapter = createAdapter()

        contentView.setAdapter(adapter)
        tabs!!.setViewPager(contentView)

        val margin: Int = Math.max(getPageMargin(), 0)
        contentView.setPageMargin(margin)

        if (margin > 0) {
            contentView.setPageMarginDrawable(getViewPagerDividerDrawable() ?: 0)
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.cb_activity_toolbar_tabs
    }

    /**
     * The margin between the pages in the [ViewPager]
     *
     * @return margin between pages in [ViewPager]
     */
    protected fun getPageMargin(): Int {
        return 20
    }

    /**
     * The [android.graphics.drawable.Drawable] which will be displayed between the pages in
     * the [ViewPager] if `[.getPageMargin] > 0`
     *
     * @return divider [android.graphics.drawable.Drawable] for the [ViewPager]
     */
    protected fun getViewPagerDividerDrawable(): Int? {
        return R.drawable.cb_viewpager_divider
    }

    /**
     * Creates the [A] for the [ViewPager].
     * Called in [.onCreate]
     *
     * @return [A]
     */
    protected abstract fun createAdapter(): A
}