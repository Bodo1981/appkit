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
package com.christianbahl.appkit.fragment;

import android.os.Bundle;
import android.view.View;
import com.christianbahl.appkit.presenter.CBPresenter;
import com.christianbahl.appkit.view.CBView;

/**
 * A base fragment with a presenter
 *
 * <p>
 * To create the {@link P} you have to implement the {@link #createPresenter()} method. This will
 * be called in {@link #onViewCreated(View, Bundle)}. If the {@link P} was created successfully
 * {@link #onPresenterCreated()} will be called.
 * </p>
 *
 * @author Christian Bahl
 * @see CBFragment
 */
public abstract class CBFragmentPresenter<P extends CBPresenter> extends CBFragment
    implements CBView {

  protected P presenter;

  @SuppressWarnings("unchecked") @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (presenter == null) {
      presenter = createPresenter();

      if (presenter == null) {
        throw new NullPointerException(
            "Presenter is null! Did you forget to create presenter in createPresenter()?");
      }
    }

    presenter.attachView(this);
    onPresenterCreated();
  }

  @Override public void onDestroy() {
    super.onDestroy();

    if (presenter != null) {
      presenter.detachView(getRetainInstance());
    }
  }

  /**
   * Creates the {@link P}. <br>
   * Called in {@link #onViewCreated(View, Bundle)}
   *
   * @return {@link P}
   */
  protected abstract P createPresenter();

  /**
   * Will be called after the {@link P} was created and only if the presenter is created in {@link
   * #onViewCreated(View, Bundle)}
   */
  protected abstract void onPresenterCreated();
}
