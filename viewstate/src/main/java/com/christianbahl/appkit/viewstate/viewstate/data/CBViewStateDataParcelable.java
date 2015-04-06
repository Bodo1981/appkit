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
package com.christianbahl.appkit.viewstate.viewstate.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.christianbahl.appkit.view.CBMvpView;
import com.christianbahl.appkit.viewstate.viewstate.CBViewStateMvpRestoreableParcelable;

/**
 * A view state implementation which implements {@link CBViewStateMvpRestoreableParcelable} and
 * uses a {@link Parcelable} for the data
 *
 * @param <D> data type of the {@link Parcelable}
 * @param <V> type of mvp view
 * @author Christian Bahl
 */
public class CBViewStateDataParcelable<D extends Parcelable, V extends CBMvpView<D>>
    extends CBViewStateMvpRestoreableParcelable<D, V> {

  public static final Parcelable.Creator<CBViewStateDataParcelable> CREATOR =
      new Parcelable.Creator<CBViewStateDataParcelable>() {
        @Override public CBViewStateDataParcelable createFromParcel(Parcel source) {
          return new CBViewStateDataParcelable(source);
        }

        @Override public CBViewStateDataParcelable[] newArray(int size) {
          return new CBViewStateDataParcelable[size];
        }
      };

  private static final String KEY_BUNDLE_PARCELABLE =
      "com.christianbahl.appkit.viewstate.data.CBViewStateDataParcelable";

  public CBViewStateDataParcelable() {

  }

  private CBViewStateDataParcelable(Parcel source) {
    readFromParcel(source);
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);

    Bundle b = new Bundle();
    b.putParcelable(KEY_BUNDLE_PARCELABLE, loadedData);
    dest.writeBundle(b);
  }

  @Override protected void readFromParcel(Parcel source) {
    super.readFromParcel(source);

    Bundle b = source.readBundle();
    if (b != null) {
      loadedData = b.getParcelable(KEY_BUNDLE_PARCELABLE);
    }
  }
}
