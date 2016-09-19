package com.christianbahl.appkit.rx2;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>
 * Sets the typical threads for subscribeOn (Schedulers.io()) and observeOn(AndroidSchedulers.mainThread())
 * for Android.
 * </p>
 *
 * @param <T> data of the {@link Maybe}
 * @author Christian Bahl
 * @see CBMaybeTransformer
 */
public class CBMaybeTransformer<T> implements MaybeTransformer<T, T> {

  public CBMaybeTransformer() {
  }

  @Override public MaybeSource<T> apply(Maybe<T> maybe) throws Exception {
    return maybe.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}
