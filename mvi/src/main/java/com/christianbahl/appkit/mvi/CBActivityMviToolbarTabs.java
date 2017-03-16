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
package com.christianbahl.appkit.mvi;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * <p>
 * An activity which uses the Model-View-Intent architecture.<br/>
 * It also adds a {@link Toolbar} on top and has a {@link ViewPager} with a {@link TabLayout}.
 * </p>
 *
 * <p>
 * The standard layout implements all necessary views. You can override the default layout in {@link #getLayoutRes()}. But be careful, you
 * have to provide the necessary views!
 * </p>
 *
 * <p>
 * The layout has to contain a {@link ViewPager} view with id specified in {@link #createContentView()} <b>default:</b>
 * <code>R.id.contentView</code>.
 * </p>
 *
 * <p>
 * You also have to provide a {@link TabLayout} with id specified in {@link #createTabLayout()} ()} <b>default:</b> <code>R.id.tabs</code>.
 * </p>
 *
 * <p>
 * Implement {@link #createAdapter()} to provide your {@link PagerAdapter} for displaying the tabs
 * </p>
 *
 * <p>
 * There a two functions to customize the {@link ViewPager}<br/>
 *
 * * getPageMargin(): sets the margin between the pages<br/>
 * * getViewPagerDividerDrawable(): sets divider {@link Drawable} between the pages
 * </p>
 *
 * @author Christian Bahl
 * @see CBActivityMviToolbar
 */
public abstract class CBActivityMviToolbarTabs<V extends MvpView, P extends MviPresenter<V, ?>, A extends PagerAdapter>
    extends CBActivityMviToolbar<ViewPager, V, P> {

  protected TabLayout tabs;
  protected A adapter;

  @Override protected void onViewCreated() {
    super.onViewCreated();

    tabs = createTabLayout();
    if (tabs == null) {
      throw new NullPointerException("TabLayout is null! Do you return null in your createTabLayout() method?");
    }

    adapter = createAdapter();
    if (adapter == null) {
      throw new NullPointerException("Adapter is null! Do you return null in your createAdapter() method?");
    }

    contentView.setAdapter(adapter);
    tabs.setupWithViewPager(contentView, true);

    int margin = Math.max(getPageMargin(), 0);

    if (margin > 0) {
      contentView.setPageMargin(margin);

      Integer pageMarginDrawable = getViewPagerDividerDrawable();
      if (pageMarginDrawable != null) {
        contentView.setPageMarginDrawable(pageMarginDrawable);
      }
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    tabs = null;
    adapter = null;
  }

  @Override @NonNull protected Integer getLayoutRes() {
    return R.layout.cb_activity_lce_toolbar_tabs;
  }

  /**
   * <p>
   * Create the tab layout.
   * </p>
   *
   * @return tab layout
   */
  @NonNull protected TabLayout createTabLayout() {
    return (TabLayout) findViewById(R.id.tabs);
  }

  /**
   * <p>
   * The margin between the pages in the {@link ViewPager}.
   * </p>
   *
   * @return margin between pages in {@link ViewPager}
   */
  protected int getPageMargin() {
    return 20;
  }

  /**
   * <p>
   * The {@link Drawable} which will be displayed between the pages in the {@link ViewPager}
   * if {@link #getPageMargin()} > 0.
   * </p>
   *
   * @return divider {@link Drawable} for the {@link ViewPager}
   */
  @Nullable protected Integer getViewPagerDividerDrawable() {
    return R.drawable.cb_viewpager_divider;
  }

  /**
   * <p>
   * Creates the {@link A} for the {@link ViewPager}.<br/>
   * Called in {@link #onCreate(Bundle)}.
   * </p>
   *
   * @return {@link A}
   */
  @NonNull protected abstract A createAdapter();
}
