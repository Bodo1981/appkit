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
package com.christianbahl.appkit.rx;

import com.christianbahl.appkit.view.CBMvpView;
import rx.Observable;
import rx.Subscriber;

/**
 * A base presenter wich uses the Model-View-Presenter architecture in connection with rxjava.
 *
 * <p>
 * It implements the {@link Subscriber} functions:
 * <ul>
 * <li><b>onNext</b>: calls setData(D) on the {@link CBMvpView}</li>
 * <li><b>onCompleted</b>: calls showContent() on the {@link CBMvpView}</li>
 * <li><b>onError</b>: calls showError(Exception, boolean) on the {@link CBMvpView}</li>
 * </ul>
 * </p>
 *
 * <p>
 * If you subscribe to the {@link Observable} in {@link #subscribe(Observable, boolean)} the
 * showLoading(boolean) on the {@link CBMvpView} is automatically called for you.
 * </p>
 *
 * @author Christian Bahl
 * @see CBRxPresenter
 */
public class CBRxMvpPresenter<V extends CBMvpView<D>, D> extends CBRxPresenter<V, D> {

  protected boolean isContentVisible;

  /**
   * Subscribe to the passed {@link Observable} and call showLoading(boolean)
   *
   * @param observable {@link Observable}
   * @param isContentVisible is the content visible
   */
  protected void subscribe(Observable<D> observable, boolean isContentVisible) {
    this.isContentVisible = isContentVisible;

    if (!isContentVisible && isViewAttached()) {
      getView().showLoading(isContentVisible);
    }

    super.subscribe(observable);
  }

  @Override public void onCompleted() {
    if (isViewAttached()) {
      getView().showContent();
    }
  }

  @Override public void onError(Throwable e) {
    if (isViewAttached()) {
      getView().showError(new Exception(e), isContentVisible);
    }
  }

  @Override public void onNext(D d) {
    if (isViewAttached()) {
      getView().setData(d);
    }
  }
}
