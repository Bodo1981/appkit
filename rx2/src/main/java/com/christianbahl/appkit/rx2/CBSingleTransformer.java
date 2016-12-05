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
package com.christianbahl.appkit.rx2;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>
 * Sets the typical threads for subscribeOn (Schedulers.io()) and observeOn(AndroidSchedulers.mainThread())
 * for Android.
 * </p>
 *
 * @param <T> data of the {@link Single}
 * @author Christian Bahl
 * @see CBSingleTransformer
 */
public class CBSingleTransformer<T> implements SingleTransformer<T, T> {

  public CBSingleTransformer() {
  }

  @Override public SingleSource<T> apply(Single<T> single) {
    return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}