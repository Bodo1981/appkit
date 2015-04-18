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
package com.christianbahl.appkit.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import com.astuetz.PagerSlidingTabStrip;
import com.christianbahl.appkit.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * An activity which uses the Model-View-Presenter architecture. It also adds a {@link Toolbar} on
 * top and has a {@link ViewPager} with {@link PagerSlidingTabStrip}.
 *
 * <p>
 * The layout has to contain a view with id <code>R.layout.conten_view</code> which must be of
 * type {@link ViewPager}. You also have to provide a view with id <code>R.layout.tabs</code> of
 * type {@link PagerSlidingTabStrip}.
 * </p>
 *
 * <p>
 * The standard layout implements all necessary views. You can override the default layout in
 * {@link #getLayoutRes()}. But be careful, you have to provide the necessary views!
 * </p>
 *
 * <p>
 * There a two functions to customize the {@link ViewPager}
 * <ul>
 * <li>getPageMargin(): sets the margin between the pages</li>
 * <li>getViewPagerDividerDrawable(): sets divider {@link Drawable} between the pages</li>
 * </ul>
 * </p>
 *
 * @author Christian Bahl
 */
public abstract class CBActivityMvpToolbarTabs<A extends PagerAdapter, D, V extends MvpLceView<D>, P extends MvpPresenter<V>>
    extends CBActivityMvpToolbar<ViewPager, D, V, P> {

  protected PagerSlidingTabStrip tabs;
  protected A adapter;

  @Override protected void onMvpViewCreated() {
    tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
    if (tabs == null) {
      throw new IllegalStateException("The tabs is not specified. "
          + "You have to provide a View with R.id.tabs in your inflated xml layout");
    }

    adapter = createAdapter();
    if (adapter == null) {
      throw new IllegalArgumentException(
          "Adapter is null. Did you forget to create the adapter in createAdapter()?");
    }

    contentView.setAdapter(adapter);
    contentView.setPageMargin(getPageMargin());

    tabs.setViewPager(contentView);

    Integer pageMarginDrawable = getViewPagerDividerDrawable();
    if (pageMarginDrawable != null) {
      contentView.setPageMarginDrawable(pageMarginDrawable);
    }
  }

  @Override protected Integer getLayoutRes() {
    return R.layout.cb_activity_toolbar_tabs;
  }

  /**
   * The margin between the pages in the {@link ViewPager}
   *
   * @return margin between pages in {@link ViewPager}
   */
  protected int getPageMargin() {
    return 20;
  }

  /**
   * The {@link Drawable} which will be displayed between the pages in the {@link ViewPager}
   * if <code>{@link #getPageMargin()} > 0</code>
   *
   * @return divider {@link Drawable} for the {@link ViewPager}
   */
  protected Integer getViewPagerDividerDrawable() {
    return R.drawable.cb_viewpager_divider;
  }

  /**
   * Creates the {@link A} for the {@link ViewPager}. <br>
   * Called in {@link #onCreate(Bundle)}
   *
   * @return {@link A}
   */
  protected abstract A createAdapter();
}
