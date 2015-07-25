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
package com.christianbahl.appkit.core.dagger1.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import com.christianbahl.appkit.core.dagger1.R;
import com.hannesdorfmann.mosby.dagger1.Dagger1MosbyActivity;

/**
 * <p>
 * An activity which uses the Model-View-Presenter architecture and Dagger 1 for dependency
 * injection.
 * </p>
 *
 * <p>
 * The layout must have a {@link ViewGroup} for the {@link Fragment} with the id
 * <code>R.layout.contentView</code>
 * </p>
 *
 * <p>
 * The standard layout implements all necessary views. You can override the default layout in
 * {@link #getLayoutRes}. But be careful, you have to provide the necessary views!
 * </p>
 *
 * <p>
 * You have to override the {@link #createFragmentToDisplay} to create the {@link Fragment} which
 * should be displayed.
 * </p>
 *
 * @author Christian Bahl
 * @see Dagger1MosbyActivity
 */
public abstract class CBDagger1ActivityFragment extends Dagger1MosbyActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getLayoutRes());

    if (getIntent() != null && getIntent().getExtras() != null) {
      readExtras(getIntent().getExtras());
    }

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.contentView, createFragmentToDisplay())
          .commit();
    }
  }

  /**
   * Provide the layout res id for the activity.
   *
   * @return layout res id
   */
  protected Integer getLayoutRes() {
    return R.layout.cb_activity_fragment;
  }

  /**
   * Handle extra bundle data
   *
   * @param bundle bundle with extras passed to activity
   */
  protected void readExtras(Bundle bundle) {

  }

  /**
   * Returns the {@link Fragment} which should be displayed by this activity.
   *
   * @return {@link Fragment}
   */
  protected abstract Fragment createFragmentToDisplay();
}
