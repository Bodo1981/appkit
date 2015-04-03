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
package com.christianbahl.appkit.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.view.CBView;

/**
 * A base interface which must be used by every presenter which will be used in the
 * Model-View-Presenter architecture.
 *
 * @author Christian Bahl
 */
public interface CBPresenterInterface<V extends CBView> {

  /**
   * Attaches the {@link V} to the presenter
   *
   * @param view {@link V}
   */
  void attachView(V view);

  /**
   * Called if the {@link Activity} / {@link Fragment} gets destroyed.
   *
   * @param retainInstanceState true if this presenter instance will be retained (i.e. durring
   * orientation changes) and will not be new instantiated, otherwise false. You may have to cancel
   * any running background threads, if retainInstanceState == false.
   */
  void detachView(boolean retainInstanceState);
}
