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

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.hannesdorfmann.mosby3.mvi.MviActivity;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * <p>
 * An activity which uses the Model-View-Intent architecture and adds a {@link Toolbar} on top.
 * </p>
 *
 * <p>
 * The standard layout implements all necessary views. You can override the default layout in {@link #getLayoutRes}. But be careful, you
 * have to provide the necessary views!
 * </p>
 *
 * <p>
 * You have to provide a toolbar. Default id <code>R.id.toolbar</code> but it can also be overriden {@link #createToolbar()}.
 * </p>
 *
 * <p>
 * As loadingView a {@link ContentLoadingProgressBar} will be used. This can be changed by overriding {@link #createLoadingView()}. If you
 * only want to change the loading view color for the default loadingView you have to override the color attribute
 * <code>R.color.cb_progress_color</code>.
 * </p>
 *
 * @author Christian Bahl
 * @see MviActivity
 */
public abstract class CBActivityMviToolbar<CV extends View, V extends MvpView, P extends MviPresenter<V, ?>> extends MviActivity<V, P> {

  protected View loadingView;
  protected CV contentView;
  protected TextView errorView;
  protected Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getIntent() != null && getIntent().getExtras() != null) {
      readExtras(getIntent().getExtras());
    }

    Integer layoutRes = getLayoutRes();
    if (layoutRes == null) {
      throw new NullPointerException("LayoutRes is null! Did you return null in getLayoutRes()?");
    }
    setContentView(layoutRes);
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    loadingView = createLoadingView();
    contentView = createContentView();
    errorView = createErrorView();
    toolbar = createToolbar();

    if (loadingView == null) {
      throw new NullPointerException("Loading view is null! Do you return null in your createLoadingView() method?");
    } else if (contentView == null) {
      throw new NullPointerException("Content view is null! Do you return null in your createContentView() method?");
    } else if (errorView == null) {
      throw new NullPointerException("Error view is null! Do you return null in your createErrorView() method?");
    } else if (toolbar == null) {
      throw new NullPointerException("Toolbar is null! Do you return null in your createToolbar() method?");
    }

    setSupportActionBar(toolbar);

    onViewCreated();
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    loadingView = null;
    contentView = null;
    errorView = null;
    toolbar = null;
  }

  /**
   * <p>
   * Create the loading view
   * </p>
   *
   * @return loading view
   */
  @NonNull protected View createLoadingView() {
    ContentLoadingProgressBar loadingView = (ContentLoadingProgressBar) findViewById(R.id.loadingView);
    loadingView.getIndeterminateDrawable()
        .setColorFilter(ContextCompat.getColor(this, R.color.cb_progressbar_color), PorterDuff.Mode.SRC_ATOP);

    return loadingView;
  }

  /**
   * <p>
   * Create the content view
   * </p>
   *
   * @return content view
   */
  @SuppressWarnings("unchecked") @NonNull protected CV createContentView() {
    return (CV) findViewById(R.id.contentView);
  }

  /**
   * <p>
   * Create the error view
   * </p>
   *
   * @return error view
   */
  @NonNull protected TextView createErrorView() {
    return (TextView) findViewById(R.id.errorView);
  }

  /**
   * <p>
   * Create the toolbar
   * </p>
   *
   * @return toolbar
   */
  @NonNull protected Toolbar createToolbar() {
    return (Toolbar) findViewById(R.id.toolbar);
  }

  /**
   * <p>
   * Provide the layout res id for the activity.
   * </p>
   *
   * @return layout res id
   */
  @NonNull protected Integer getLayoutRes() {
    return R.layout.cb_activity_lce_toolbar;
  }

  /**
   * <p>
   * Called after views and toolbar are created in {@link #onContentChanged()}.
   * </p>
   */
  protected void onViewCreated() {
  }

  /**
   * <p>
   * Handle extra bundle data. Is only called if {@link #getIntent()} != null and intent has extras. If there are any extras in the intent
   * this method is called directly before {@link #setContentView(int)} in {@link #onCreate(Bundle)}.
   * </p>
   *
   * @param bundle bundle with extras passed to activity
   */
  protected void readExtras(@NonNull Bundle bundle) {
  }
}
