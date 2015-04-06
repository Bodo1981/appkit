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
import java.util.ArrayList;
import java.util.List;

/**
 * A view state implementation which implements {@link CBViewStateMvpRestoreableParcelable} and
 * uses a {@link List} with {@link Parcelable} data
 *
 * <p>
 * <b>The {@link List} has to be of type {@link ArrayList}</b>
 * </p>
 *
 * @param <D> data type of the {@link List}
 * @param <V> type of mvp view
 * @author Christian Bahl
 */
public class CBViewStateDataList<D extends Parcelable, V extends CBMvpView<List<D>>>
    extends CBViewStateMvpRestoreableParcelable<List<D>, V> {

  public static final Parcelable.Creator<CBViewStateDataList> CREATOR =
      new Parcelable.Creator<CBViewStateDataList>() {
        @Override public CBViewStateDataList createFromParcel(Parcel source) {
          return new CBViewStateDataList(source);
        }

        @Override public CBViewStateDataList[] newArray(int size) {
          return new CBViewStateDataList[size];
        }
      };

  private static final String KEY_BUNDLE_ARRAY_LIST =
      "com.christianbahl.appkit.viewstate.data.CBViewStateDataList";

  public CBViewStateDataList() {

  }

  private CBViewStateDataList(Parcel source) {
    readFromParcel(source);
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);

    if (loadedData != null && !(loadedData instanceof ArrayList)) {
      throw new ClassCastException(
          "Your List has to be of type ArrayList but your List is of type " + loadedData.getClass()
              .getCanonicalName());
    }

    // Content
    Bundle b = new Bundle();
    b.putParcelableArrayList(KEY_BUNDLE_ARRAY_LIST, (ArrayList<D>) loadedData);
    dest.writeBundle(b);
  }

  @Override protected void readFromParcel(Parcel source) {
    super.readFromParcel(source);

    Bundle b = source.readBundle();
    if (b != null) {
      loadedData = b.getParcelableArrayList(KEY_BUNDLE_ARRAY_LIST);
    }
  }
}
