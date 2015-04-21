package com.christianbahl.appkit.activity

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.astuetz.PagerSlidingTabStrip
import com.christianbahl.appkit.R
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView

/**
 * An activity which uses the Model-View-Presenter architecture. It also adds a [Toolbar] on
 * top and has a [ViewPager] with [PagerSlidingTabStrip].
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
 *  * getViewPagerDividerDrawable(): sets divider [Drawable] between the pages
 *
 * @author Christian Bahl
 */
public abstract class CBActivityMvpToolbarTabs<A : PagerAdapter, D, V : MvpLceView<D>, P : MvpPresenter<V>> : CBActivityMvpToolbar<ViewPager, D, V, P>() {

    protected var tabs: PagerSlidingTabStrip? = null
    protected var adapter: A? = null

    override fun onMvpViewCreated() {
        tabs = findViewById(R.id.tabs) as PagerSlidingTabStrip
        if (tabs == null) {
            throw IllegalStateException(
                    "The tabs is not specified. " + "You have to provide a View with R.id.tabs in your inflated xml layout")
        }

        adapter = createAdapter()
        if (adapter == null) {
            throw IllegalArgumentException(
                    "Adapter is null. Did you forget to create the adapter in createAdapter()?")
        }

        contentView.setAdapter(adapter)
        contentView.setPageMargin(getPageMargin())

        tabs!!.setViewPager(contentView)

        val pageMarginDrawable = getViewPagerDividerDrawable()
        if (pageMarginDrawable != null) {
            contentView.setPageMarginDrawable(pageMarginDrawable)
        }
    }

    override fun getLayoutRes(): Int? {
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
     * The [Drawable] which will be displayed between the pages in the [ViewPager]
     * if `[.getPageMargin] > 0`
     *
     * @return divider [Drawable] for the [ViewPager]
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