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
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.christianbahl.appkit.core.R;

/**
 * <p>
 * An activity which displays a simple {@link Fragment}.
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
 */
public abstract class CBActivityFragment extends AppCompatActivity {

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

    if (savedInstanceState == null) {
      Fragment fragment = createFragmentToDisplay();

      if (fragment == null) {
        throw new NullPointerException(
            "Fragment is null. Did you return null in createFragmentToDisplay()?");
      }

      getSupportFragmentManager().beginTransaction().replace(R.id.contentView, fragment).commit();
    }
  }

  /**
   * <p>
   * Provide the layout res id for the activity.
   * </p>
   *
   * @return layout res id
   */
  @NonNull protected Integer getLayoutRes() {
    return R.layout.cb_activity_fragment;
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

  /**
   * <p>
   * Returns the {@link Fragment} which should be displayed by this activity.
   * </p>
   *
   * @return {@link Fragment}
   */
  @NonNull protected abstract Fragment createFragmentToDisplay();
}
