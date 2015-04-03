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
package com.christianbahl.appkit.retrofit;

import com.christianbahl.appkit.presenter.CBPresenter;
import com.christianbahl.appkit.view.CBMvpView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A base retrofit presenter which uses the Model-View-Presenter architecture.
 *
 * <p>
 * It has a protected inner class {@link CBRetrofitMvpCallback} which implements the {@link
 * Callback}. It calls the MVP (showLoading, showContent, setData, showError) methods for you. This
 * inner class can be used as retrofit callback.
 * </p>
 *
 * @author Christian Bahl
 * @see CBPresenter
 */
public class CBRetrofitMvpPresenter<V extends CBMvpView<D>, D> extends CBPresenter<V> {

  protected class CBRetrofitMvpCallback implements Callback<D> {

    private final boolean isContentVisible;

    public CBRetrofitMvpCallback(boolean isContentVisible) {
      this.isContentVisible = isContentVisible;

      if (isViewAttached()) {
        getView().showLoading(isContentVisible);
      }
    }

    @Override public void success(D d, Response response) {
      if (isViewAttached()) {
        getView().setData(d);
        getView().showContent();
      }
    }

    @Override public void failure(RetrofitError error) {
      if (isViewAttached()) {
        getView().showError(error, isContentVisible);
      }
    }
  }
}