package com.christianbahl.appkit.rx2;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

/**
 * <p>
 * Sets the typical threads for subscribeOn (Schedulers.io()) and observeOn(AndroidSchedulers.mainThread())
 * for Android.
 * </p>
 *
 * @param <T> data of the {@link Flowable}
 * @author Christian Bahl
 * @see CBFlowableTransformer
 */
public class CBFlowableTransformer<T> implements FlowableTransformer<T, T> {

  public CBFlowableTransformer() {
  }

  @Override public Publisher<? extends T> apply(Flowable<T> flowable) throws Exception {
    return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}
