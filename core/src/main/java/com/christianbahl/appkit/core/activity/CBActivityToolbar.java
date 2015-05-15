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
import android.support.v7.widget.Toolbar;
import com.christianbahl.appkit.core.R;
import com.hannesdorfmann.mosby.MosbyActivity;

/**
 * An activity which adds a {@link Toolbar} on top.
 *
 * This activity also enables {@link ActionBar#setDisplayShowHomeEnabled(boolean)} so the
 * toolbar will show the title. If you do not want this in your activity you can override this
 * in {@link #isDisplayShowTitleEnabled()}.
 *
 * The standard layout implements all necessary views. You can override the default layout in
 * {@link #getLayoutRes}. But be careful, you have to provide the necessary views!
 *
 * @author Christian Bahl
 * @see MosbyActivity
 */
public abstract class CBActivityToolbar extends MosbyActivity {

  protected Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getLayoutRes());
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    // throws an exception if toolbar is not a toolbar
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
   * Should the title be displayed in the toolbar.
   *
   * @return `true` if title should be displayed in toolbar otherwise `false`
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
    return R.layout.cb_activity_toolbar;
  }
}
