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
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * A very base empty activity.
 *
 * <p>
 * Uses <b>Icepick</b>: Annotation processing to make working with onSaveInstanceState()
 * much easier. Simply use <code>@Icicle</code> to mark fields which state should be saved in
 * onSaveInstanceState().
 * </p>
 *
 * <p>
 * Uses <b>Butterknife</b>: Annotation processing to inject views. Simple use
 * <code>@InjectView</code> to mark views which should be initialised.
 * </p>
 *
 * @author Christian Bahl
 */
public abstract class CBActivity extends ActionBarActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Icepick.restoreInstanceState(this, savedInstanceState);

    Integer layoutResId = getLayoutResId();
    if (layoutResId != null) {
      setContentView(layoutResId);
    }
  }

  @Override public void onSupportContentChanged() {
    super.onSupportContentChanged();

    ButterKnife.inject(this);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    Icepick.saveInstanceState(this, outState);
  }

  /**
   * Return the layout res id. <br>
   * Called in {@link #onCreate(Bundle)}
   *
   * @return layout res id
   */
  protected abstract Integer getLayoutResId();
}
