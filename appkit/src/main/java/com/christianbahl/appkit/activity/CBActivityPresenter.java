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
import com.christianbahl.appkit.presenter.CBBasePresenter;
import com.christianbahl.appkit.view.CBView;

/**
 * A base activity with a presenter
 *
 * <p>
 * To create the presenter you have to implement the {@link #createPresenter()} method. This will
 * be called in {@link #onCreate(Bundle)}.
 * </p>
 *
 * @author Christian Bahl
 * @see CBActivity
 */
public abstract class CBActivityPresenter<P extends CBBasePresenter> extends CBActivity
    implements CBView {

  protected P presenter;

  @SuppressWarnings("unchecked") @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    presenter = createPresenter();
    if (presenter == null) {
      throw new IllegalArgumentException(
          "Presenter is null. Did you return null in createPresenter()?");
    }
    presenter.setView(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    if (presenter != null) {
      presenter.onDestroy(false);
    }
  }

  /**
   * Creates the {@link P} instance. <br />
   * Called in {@link #onCreate(Bundle)}
   *
   * @return {@link P}
   */
  protected abstract P createPresenter();
}
