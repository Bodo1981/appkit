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
package com.christianbahl.appkit.rx.presenter;

import com.christianbahl.appkit.rx.CBAndroidSchedulerTransformer;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import rx.Observable;
import rx.Subscriber;

/**
 * <p>
 * Mvp Lce presenter which handles subscribing and unsubscribing for {@link Observable}.
 * </p>
 *
 * <p>
 * The functions for Lce are called automatically when calling {@link #subscribe(Observable,
 * boolean)}.
 * </p>
 *
 * @author Christian Bahl
 * @see MvpBasePresenter
 */
public class CBLceRxPresenter<V extends MvpLceView<M>, M> extends MvpBasePresenter<V> {

  protected Subscriber<M> subscriber;

  public CBLceRxPresenter() {
  }

  protected void unsubscribe() {
    if (subscriber != null && !subscriber.isUnsubscribed()) {
      subscriber.unsubscribe();
    }

    subscriber = null;
  }

  public void subscribe(Observable<M> observable, final boolean contentPresent) {
    if (isViewAttached()) {
      getView().showLoading(contentPresent);
    }

    unsubscribe();

    subscriber = new Subscriber<M>() {
      @Override public void onCompleted() {
        CBLceRxPresenter.this.onCompleted();
      }

      @Override public void onError(Throwable e) {
        CBLceRxPresenter.this.onError(e, contentPresent);
      }

      @Override public void onNext(M m) {
        CBLceRxPresenter.this.onNext(m);
      }
    };

    observable = applyScheduler(observable);
    observable.subscribe(subscriber);
  }

  protected Observable<M> applyScheduler(Observable<M> observable) {
    return observable.compose(new CBAndroidSchedulerTransformer<M>());
  }

  protected void onCompleted() {
    if (isViewAttached()) {
      getView().showContent();
    }

    unsubscribe();
  }

  protected void onError(Throwable e, boolean contentPresent) {
    if (isViewAttached()) {
      getView().showError(e, contentPresent);
    }

    this.unsubscribe();
  }

  protected void onNext(M data) {
    if (isViewAttached()) {
      getView().setData(data);
    }
  }

  @Override public void detachView(boolean retainInstance) {
    super.detachView(retainInstance);

    if (!retainInstance) {
      unsubscribe();
    }
  }
}
