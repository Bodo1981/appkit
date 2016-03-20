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

import android.support.annotation.NonNull;
import com.christianbahl.appkit.rx.CBAndroidSchedulerTransformer;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

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

  protected CompositeSubscription compositeSubscription;
  protected CBAndroidSchedulerTransformer<M> transformer;

  public CBLceRxPresenter() {
    this.transformer = new CBAndroidSchedulerTransformer<>();
  }

  public void subscribe(Observable<M> observable, final boolean contentPresent) {
    if (isViewAttached()) {
      getView().showLoading(contentPresent);
    }

    if (compositeSubscription == null) {
      compositeSubscription = new CompositeSubscription();
    }

    compositeSubscription.add(applyScheduler(observable).subscribe(new Subscriber<M>() {
      @Override public void onCompleted() {
        CBLceRxPresenter.this.onCompleted();
      }

      @Override public void onError(Throwable e) {
        CBLceRxPresenter.this.onError(e, contentPresent);
      }

      @Override public void onNext(M m) {
        CBLceRxPresenter.this.onNext(m);
      }
    }));
  }

  @NonNull protected Observable<M> applyScheduler(Observable<M> observable) {
    return observable.compose(transformer);
  }

  protected void unsubscribe() {
    if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
      compositeSubscription.unsubscribe();
    }

    compositeSubscription = null;
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
