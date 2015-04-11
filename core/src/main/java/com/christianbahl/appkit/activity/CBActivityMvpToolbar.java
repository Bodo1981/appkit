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

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.christianbahl.appkit.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * An activity which uses the Model-View-Presenter architecture and adds a {@link Toolbar} on top.
 * <p>
 * This activity also enables {@link ActionBar#setDisplayHomeAsUpEnabled(boolean)} so the toolbar
 * will show the title. If you don´t want this in your activity you can override this
 * in {@link #isDisplayShowTitleEnabled()}.
 * </p>
 *
 * <p>
 * The standard layout implements all necessary views. You can override the default layout in
 * {@link #getLayoutRes()} ()}. But be careful, you have to provide the necessary views!
 * </p>
 *
 * @author Christian Bahl
 * @see MvpLceActivity
 */
public abstract class CBActivityMvpToolbar<CV extends View, D, V extends MvpLceView<D>, P extends MvpPresenter<V>>
    extends MvpLceActivity<CV, D, V, P> {

  protected Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Integer layoutResId = getLayoutRes();
    if (layoutResId == null) {
      throw new NullPointerException("LayoutResId is null. Do you return NULL in getLayoutRes?");
    }

    setContentView(layoutResId);
  }

  @Override public void onSupportContentChanged() {
    super.onSupportContentChanged();

    toolbar = (Toolbar) findViewById(R.id.toolbar);

    if (toolbar == null) {
      throw new IllegalStateException("The toolbar is null. "
          + "You have to provide a View with R.id.toolbar in your inflated xml layout");
    }

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(isDisplayShowTitleEnabled());

    onMvpViewCreated();

    loadData(false);
  }

  @Override protected String getErrorMessage(Throwable throwable, boolean isContentVisible) {
    return throwable.getLocalizedMessage();
  }

  /**
   * Should the title be displayed in the toolbar.
   *
   * @return <code>true</code> if title should be displayed in toolbar otherwise <code>false</code>
   */
  protected boolean isDisplayShowTitleEnabled() {
    return true;
  }

  /**
   * Provide the layout res id for the activity.
   *
   * @return layout res id
   */
  protected Integer getLayoutRes() {
    return R.layout.cb_activity_toolbar_fragment;
  }

  /**
   * Called after mvp views and toolbar are created
   */
  protected abstract void onMvpViewCreated();
}
