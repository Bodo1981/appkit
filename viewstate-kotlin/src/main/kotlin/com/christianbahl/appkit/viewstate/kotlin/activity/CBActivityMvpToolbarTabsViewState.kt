/*
 * Copyright 2015 Christian Bahl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.christianbahl.appkit.viewstate.kotlin.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.astuetz.PagerSlidingTabStrip
import com.christianbahl.appkit.viewstate.kotlin.R
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView

/**
 * @author Christian Bahl
 */
public abstract class CBActivityMvpToolbarTabsViewState<A : PagerAdapter, D, V : MvpLceView<D>, P : MvpPresenter<V>> : CBActivityMvpToolbarViewState<ViewPager, D, V, P>() {

  protected var tabs: PagerSlidingTabStrip? = null
  protected var adapter: A? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    adapter = createAdapter()!!
  }

  override fun onContentChanged() {
    super.onContentChanged()

    tabs = findViewById(R.id.tabs) as PagerSlidingTabStrip
    tabs!!.setViewPager(contentView)

    contentView.setAdapter(adapter)
    contentView.setPageMargin(getPageMargin())

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
   * The [Drawable] which will be displayed between the pages in the [ViewPager] if `[.getPageMargin] > 0`
   *
   * @return divider [Drawable] for the [ViewPager]
   */
  protected fun getViewPagerDividerDrawable(): Int? {
    return R.drawable.cb_viewpager_divider
  }

  /**
   * Creates the [A] for the [ViewPager].
   *
   * Called in [.onCreate]
   *
   * @return [A]?
   */
  protected abstract fun createAdapter(): A?
}
