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
package com.christianbahl.appkit.core.activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import com.christianbahl.appkit.core.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * <p>
 * An activity which uses the Model-View-Presenter architecture and adds a {@link Toolbar} on top.
 * </p>
 *
 * <p>
 * This activity also enables {@link ActionBar#setDisplayShowHomeEnabled(boolean)} so the
 * toolbar will show the title. If you do not want this in your activity you can override this
 * in {@link #isDisplayShowTitleEnabled()}.
 * </p>
 *
 * <p>
 * The standard layout implements all necessary views. You can override the default layout in
 * {@link #getLayoutRes}. But be careful, you have to provide the necessary views!
 * </p>
 *
 * @author Christian Bahl
 * @see MvpLceActivity
 */
public abstract class CBActivityMvpToolbar<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends MvpLceActivity<CV, M, V, P> {

  public Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Integer layoutRes = getLayoutRes();
    if (layoutRes == null) {
      throw new NullPointerException("LayoutRes is null. Did you return null in getLayoutRes()?");
    }
    setContentView(layoutRes);

    if (getIntent() != null && getIntent().getExtras() != null) {
      readExtras(getIntent().getExtras());
    }
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    if (loadingView instanceof ProgressBar) {
      ((ProgressBar) loadingView).getIndeterminateDrawable()
          .setColorFilter(ContextCompat.getColor(this, R.color.cb_progressbar_color),
              PorterDuff.Mode.SRC_ATOP);
    }

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar == null) {
      throw new NullPointerException(
          "No Toolbar found. Did you forget to add it to your layout file with the id R.id.toolbar?");
    }
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayShowTitleEnabled(isDisplayShowTitleEnabled());
    }

    onMvpViewCreated();

    if (isAutoLoadDataEnabled()) {
      loadData(false);
    }
  }

  @Override protected String getErrorMessage(Throwable throwable, boolean isContentVisible) {
    return throwable.getLocalizedMessage();
  }

  /**
   * <p>
   * Should the title be displayed in the toolbar.
   * </p>
   *
   * @return <code>true</code> if title should be displayed in toolbar otherwise <code>false</code>
   */
  protected boolean isDisplayShowTitleEnabled() {
    return true;
  }

  /**
   * <p>
   * Should the {@link #loadData(boolean)} method be called in {@link #onContentChanged()} or not.
   * </p>
   *
   * @return <code>true</code> if loadData should be called automatically, otherwise
   * <code>false</code>
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
    return R.layout.cb_activity_mvp_toolbar;
  }

  /**
   * <p>
   * Called after mvp views and toolbar are created.
   * </p>
   */
  protected void onMvpViewCreated() {

  }

  /**
   * <p>
   * Handle extra bundle data.
   * </p>
   *
   * @param bundle bundle with extras passed to activity
   */
  protected void readExtras(@NonNull Bundle bundle) {

  }
}
