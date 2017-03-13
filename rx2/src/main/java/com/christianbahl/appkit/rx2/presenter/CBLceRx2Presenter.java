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
package com.christianbahl.appkit.rx2.presenter;

import android.support.annotation.NonNull;
import com.christianbahl.appkit.rx2.CBFlowableTransformer;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * <p>
 * Mvp Lce presenter which handles subscribing and unsubscribing for {@link Flowable}.
 * </p>
 *
 * <p>
 * The functions for Lce are called automatically when calling {@link #subscribe(Flowable, boolean)}.
 * </p>
 *
 * @author Christian Bahl
 * @see MvpBasePresenter
 */
public class CBLceRx2Presenter<V extends MvpLceView<M>, M> extends MvpBasePresenter<V> {

  protected CompositeDisposable compositeDisposable = new CompositeDisposable();
  protected CBFlowableTransformer<M> flowableTransformer;

  public CBLceRx2Presenter() {
    this.flowableTransformer = new CBFlowableTransformer<>();
  }

  public void subscribe(Flowable<M> flowable, final boolean contentPresent) {
    compositeDisposable.add(applyScheduler(flowable).subscribeWith(new ResourceSubscriber<M>() {
      @Override public void onNext(M m) {
        CBLceRx2Presenter.this.onNext(m);
      }

      @Override public void onError(Throwable t) {
        CBLceRx2Presenter.this.onError(t, contentPresent);
      }

      @Override public void onComplete() {
        CBLceRx2Presenter.this.onComplete();
      }
    }));
  }

  @NonNull protected Flowable<M> applyScheduler(Flowable<M> flowable) {
    return flowable.compose(flowableTransformer);
  }

  protected void clear() {
    if (compositeDisposable != null) {
      compositeDisposable.clear();
    }
  }

  protected void onError(Throwable e, boolean contentPresent) {
    if (isViewAttached()) {
      getView().showError(e, contentPresent);
    }
  }

  protected void onNext(M data) {
    if (isViewAttached()) {
      getView().setData(data);
    }
  }

  protected void onComplete() {
    if (isViewAttached()) {
      getView().showContent();
    }
  }

  @Override public void detachView(boolean retainInstance) {
    super.detachView(retainInstance);

    if (!retainInstance) {
      clear();
    }
  }
}
