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
import com.christianbahl.appkit.presenter.CBPresenterInterface;
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
public abstract class CBActivityPresenter<P extends CBPresenterInterface> extends CBActivity
    implements CBView {

  protected P presenter;

  @SuppressWarnings("unchecked") @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    presenter = createPresenter();
    if (presenter == null) {
      throw new IllegalArgumentException(
          "Presenter is null. Did you return null in createPresenter()?");
    }
    presenter.attachView(this);
    onPresenterCreated();
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    if (presenter != null) {
      presenter.detachView(false);
    }
  }

  /**
   * Creates the {@link P} instance. <br />
   * Called in {@link #onCreate(Bundle)}
   *
   * @return {@link P}
   */
  protected abstract P createPresenter();

  /**
   * Will be called after the {@link P} was created and only if the presenter is created in {@link
   * #onCreate(Bundle)}
   */
  protected abstract void onPresenterCreated();
}
