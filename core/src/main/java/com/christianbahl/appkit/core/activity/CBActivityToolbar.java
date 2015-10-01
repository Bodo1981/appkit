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
 */
public abstract class CBActivityToolbar extends AppCompatActivity {

  protected Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Integer layoutRes = getLayoutRes();
    if (layoutRes == null) {
      throw new NullPointerException("LayoutRes is null. Did you return null in getLayoutRes()?");
    }
    setContentView(getLayoutRes());

    if (getIntent() != null && getIntent().getExtras() != null) {
      readExtras(getIntent().getExtras());
    }
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

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
   * Provide the layout res id for the activity.
   * </p>
   *
   * @return layout res id
   */
  protected Integer getLayoutRes() {
    return R.layout.cb_activity_toolbar;
  }

  /**
   * <p>
   * Handle extra bundle data.
   * </p>
   *
   * @param bundle bundle with extras passed to activity
   */
  protected void readExtras(Bundle bundle) {

  }
}
