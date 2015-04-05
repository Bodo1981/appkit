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

import com.christianbahl.appkit.presenter.CBPresenter;
import com.christianbahl.appkit.presenter.CBPresenterInterface;
import com.christianbahl.appkit.rx.transformer.CBRxDefaultTransformer;
import com.christianbahl.appkit.view.CBView;
import java.lang.ref.WeakReference;
import rx.Observable;
import rx.Subscriber;

/**
 * A very base reactive presenter.
 *
 * <p>
 * For applying {@link Observable.Transformer} to the specified {@link Observable} you should
 * declare your transformer in {@link #setSchedulerTransformer(Observable.Transformer)}. This
 * transformer will be applied in {@link #subscribe(Observable)}.
 * </p>
 *
 * <p>
 * After creating your {@link Observable} you have to pass it into the function {@link
 * #subscribe(Observable)} to be executed.
 * </p>
 *
 * @author Christian Bahl
 * @see CBPresenter
 */
public abstract class CBRxPresenter<D, V extends CBView> extends Subscriber<D>
    implements CBPresenterInterface<V> {

  protected WeakReference<V> viewReference;
  protected Observable.Transformer<D, D> schedulerTransformer = new CBRxDefaultTransformer<>();

  /**
   * Get the attached view if it is correctly attached.
   *
   * @return {@link V} if attached otherwise <code>null</code>
   */
  public V getView() {
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
    return (viewReference != null && viewReference.get() != null);
  }

  /**
   * Set the {@link Observable.Transformer} which then will be applied in {@link
   * #subscribe(Observable)} to the passed in {@link Observable}
   *
   * @param schedulerTransformer {@link Observable.Transformer}
   */
  public void setSchedulerTransformer(Observable.Transformer<D, D> schedulerTransformer) {
    this.schedulerTransformer = schedulerTransformer;
  }

  /**
   * Executes the {@link Observable} by subscribing this presenter to the passed {@link
   * Observable}.
   *
   * <p>
   * Before subscribing to it, the {@link Observable.Transformer} specified in {@link
   * #setSchedulerTransformer(Observable.Transformer)} is applied to the {@link Observable}
   * </p>
   */
  protected void subscribe(Observable<D> observable) {
    if (observable == null) {
      throw new NullPointerException("The observable is null.");
    }

    applyScheduler(observable);
    observable.subscribe(this);
  }

  /**
   * Applies the {@link Observable.Transformer} to the {@link Observable}.
   *
   * <p>
   * Default: {@link CBRxDefaultTransformer}
   * </p>
   *
   * @param observable {@link Observable}
   * @return {@link Observable} manipulated by the {@link Observable.Transformer}
   */
  protected Observable<D> applyScheduler(Observable<D> observable) {
    return observable.compose(schedulerTransformer);
  }

  @Override public void attachView(V view) {
    viewReference = new WeakReference<>(view);
  }

  @Override public void detachView(boolean retainInstanceState) {
    if (viewReference != null) {
      viewReference.clear();
      viewReference = null;
    }

    if (!retainInstanceState) {
      unsubscribe();
    }
  }
}
