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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.christianbahl.appkit.core.R;

/**
 * <p>
 * An activity which adds a {@link Toolbar} on top.
 * </p>
 *
 * <p>
 * The standard layout implements all necessary views. You can override the default layout in
 * {@link #getLayoutRes}. But be careful, you have to provide the necessary views!
 * </p>
 *
 * <p>
 * You have to provide a toolbar. Default id <code>R.id.toolbar</code> but it can also be overriden {@link #getToolbarRes()}.
 * </p>
 *
 * @author Christian Bahl
 */
public abstract class CBActivityToolbar extends AppCompatActivity {

  public Toolbar toolbar;

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
  }

  /**
   * <p>
   * Provide the layout res id for the activity.
   * </p>
   *
   * @return layout res id
   */
  @NonNull protected Integer getLayoutRes() {
    return R.layout.cb_activity_toolbar;
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
   * Handle extra bundle data.
   * </p>
   *
   * @param bundle bundle with extras passed to activity
   */
  protected void readExtras(@NonNull Bundle bundle) {
  }
}
