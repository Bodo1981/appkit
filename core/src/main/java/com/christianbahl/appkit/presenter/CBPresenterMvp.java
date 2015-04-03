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

import com.christianbahl.appkit.view.CBMvpView;

/**
 * A presenter which implements the Model-View-Presenter architecture.
 *
 * <p>
 * Functions for show loading / content / error are provided
 * <ul>
 * <li>{@link #showLoading(boolean)}</li>
 * <li>{@link #showContent()}</li>
 * <li>{@link #showError(Exception, boolean)}</li>
 * </ul>
 * </p>
 *
 * @author Christian Bahl
 * @see CBPresenter
 */
public class CBPresenterMvp<D, V extends CBMvpView<D>> extends CBPresenter<V> {

  protected void setData(D data) {
    if (isViewAttached()) {
      getView().setData(data);
    }
  }

  protected void showLoading(boolean isContentVisible) {
    if (isViewAttached()) {
      getView().showLoading(isContentVisible);
    }
  }

  protected void showContent() {
    if (isViewAttached()) {
      getView().showContent();
    }
  }

  protected void showError(Exception e, boolean isContentVisible) {
    if (isViewAttached()) {
      getView().showError(e, isContentVisible);
    }
  }
}
