package com.christianbahl.appkit.rx2;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>
 * Sets the typical threads for subscribeOn (Schedulers.io()) and observeOn(AndroidSchedulers.mainThread())
 * for Android.
 * </p>
 *
 * @param <T> data of the {@link Observable}
 * @author Christian Bahl
 * @see CBObservableTransformer
 */
public class CBObservableTransformer<T> implements ObservableTransformer<T, T> {

  public CBObservableTransformer() {
  }

  @Override public ObservableSource<T> apply(Observable<T> observable) throws Exception {
    return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}
