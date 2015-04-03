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
package com.christianbahl.appkit.presenter;

import com.christianbahl.appkit.view.CBView;
import java.lang.ref.WeakReference;

/**
 * A base presenter which holds a {@link WeakReference} to the {@link V}. This view must be set
 * in {@link #attachView(V)}
 *
 * @author Christian Bahl
 * @see CBPresenterInterface
 */
public class CBPresenter<V extends CBView> implements CBPresenterInterface<V> {

  protected WeakReference<V> viewReference;

  public CBPresenter() {

  }

  /**
   * Get the attached view if it is correctly attached.
   *
   * @return {@link V} if attached otherwise <code>null</code>
   */
  protected V getView() {
    if (viewReference != null) {
      return viewReference.get();
    }

    return null;
  }

  /**
   * Should be called before every access to the view to check if the view is correctly attached to
   * this presenter.
   *
   * @return <code>true</code> if the view is correctly attached otherwiese <code>false</code>
   */
  public boolean isViewAttached() {
    return viewReference != null && viewReference.get() != null;
  }

  @Override public void attachView(V view) {
    viewReference = new WeakReference<>(view);
  }

  @Override public void detachView(boolean retainInstanceState) {
    if (isViewAttached()) {
      viewReference.clear();
      viewReference = null;
    }
  }
}
