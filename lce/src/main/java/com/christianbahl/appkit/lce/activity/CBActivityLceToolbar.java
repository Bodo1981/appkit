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
package com.christianbahl.appkit.lce.activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.christianbahl.appkit.lce.R;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

/**
 * <p>
 * An activity which uses the Model-View-Presenter architecture and adds a {@link Toolbar} on top.
 * </p>
 *
 * <p>
 * The standard layout implements all necessary views. You can override the default layout in {@link #getLayoutRes}. But be careful, you
 * have to provide the necessary views!
 * </p>
 *
 * <p>
 * You have to provide a toolbar. Default id <code>R.id.toolbar</code> but it can also be overriden {@link #getToolbarRes()}.
 * </p>
 *
 * <p>
 * As loadingView a {@link ContentLoadingProgressBar} will be used. This can be changed by overriding {@link #createLoadingView()}. If you
 * only want to change the loading view color for the default loadingView you have to override the color attribute
 * <code>R.color.cb_progress_color</code>.
 * </p>
 *
 * <p>
 * The errorView as default displays the {@link Throwable#getLocalizedMessage()}. Override {@link #getErrorMessage(Throwable, boolean)} to
 * change the default behavior.
 * </p>
 *
 * @author Christian Bahl
 * @see MvpLceActivity
 */
public abstract class CBActivityLceToolbar<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends MvpLceActivity<CV, M, V, P> {

  protected Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getIntent() != null && getIntent().getExtras() != null) {
      readExtras(getIntent().getExtras());
    }

    Integer layoutRes = getLayoutRes();
    if (layoutRes == null) {
      throw new NullPointerException("LayoutRes is null. Did you return null in getLayoutRes()?");
    }
    setContentView(layoutRes);
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    toolbar = (Toolbar) findViewById(getToolbarRes());
    if (toolbar == null) {
      throw new NullPointerException(
          "No Toolbar found. Did you forget to add it to your layout file with the id specified in getToolbarRes()?");
    }
    setSupportActionBar(toolbar);

    onMvpViewCreated();

    if (isAutoLoadDataEnabled()) {
      loadData(false);
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    toolbar = null;
  }

  @NonNull @Override protected View createLoadingView() {
    ContentLoadingProgressBar loadingView = (ContentLoadingProgressBar) super.createLoadingView();
    loadingView.getIndeterminateDrawable()
        .setColorFilter(ContextCompat.getColor(this, R.color.cb_progressbar_color), PorterDuff.Mode.SRC_ATOP);

    return loadingView;
  }

  @Override protected String getErrorMessage(Throwable throwable, boolean isContentVisible) {
    return throwable.getLocalizedMessage();
  }

  /**
   * <p>
   * Should the {@link #loadData(boolean)} method be called in {@link #onContentChanged()} or not.
   * </p>
   *
   * @return <code>true</code> if loadData should be called automatically otherwise <code>false</code>
   */
  protected boolean isAutoLoadDataEnabled() {
    return true;
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
   * Provide the toolbar id
   * </p>
   *
   * @return toolbar id
   */
  @NonNull protected Integer getToolbarRes() {
    return R.id.toolbar;
  }

  /**
   * <p>
   * Called after mvp views and toolbar are created in {@link #onContentChanged()}, directly before {@link #loadData(boolean)} happens.
   * </p>
   */
  protected void onMvpViewCreated() {
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
