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

import android.os.Parcel;
import android.os.Parcelable;
import com.christianbahl.appkit.view.CBMvpView;
import com.christianbahl.appkit.viewstate.viewstate.CBViewStateMvpRestoreableParcelable;
import java.io.Serializable;

/**
 * A view state implementation which implements {@link CBViewStateMvpRestoreableParcelable} and
 * uses a {@link Serializable} for the data
 *
 * @param <D> data type of the {@link Serializable}
 * @param <V> type of mvp view
 * @author Christian Bahl
 */
public class CBViewStateDataSerializable<D extends Serializable, V extends CBMvpView<D>>
    extends CBViewStateMvpRestoreableParcelable<D, V> {

  public static final Parcelable.Creator<CBViewStateDataSerializable> CREATOR =
      new Parcelable.Creator<CBViewStateDataSerializable>() {
        @Override public CBViewStateDataSerializable createFromParcel(Parcel source) {
          return new CBViewStateDataSerializable(source);
        }

        @Override public CBViewStateDataSerializable[] newArray(int size) {
          return new CBViewStateDataSerializable[size];
        }
      };

  public CBViewStateDataSerializable() {

  }

  private CBViewStateDataSerializable(Parcel in) {
    readFromParcel(in);
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);

    dest.writeSerializable(loadedData);
  }

  @SuppressWarnings("unchecked") @Override protected void readFromParcel(Parcel in) {
    super.readFromParcel(in);

    loadedData = (D) in.readSerializable();
  }
}
