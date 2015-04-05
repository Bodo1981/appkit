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
package com.christianbahl.appkit.viewstate.data;

import com.christianbahl.appkit.view.CBMvpView;
import com.christianbahl.appkit.viewstate.CBViewStateInterface;

/**
 * @author Christian Bahl
 */
public interface CBViewStateMvpInterface<D, V extends CBMvpView<D>>
    extends CBViewStateInterface<V> {

  int VIEW_STATE_SHOW_LOADING = 0;
  int VIEW_STATE_SHOW_CONTENT = 1;
  int VIEW_STATE_SHOW_ERROR = 2;

  /**
   * Sets the state to "show loading"
   *
   * @param isContentVisible is content visible
   */
  void setViewStateShowLoading(boolean isContentVisible);

  /**
   * Sets the state to "show content"
   *
   * @param loadedData {@link D}
   */
  void setViewStateShowContent(D loadedData);

  /**
   * Sets the state to "show error"
   *
   * @param e {@link Throwable}
   * @param isContentVisible is content visible
   */
  void setViewStateShowError(Throwable e, boolean isContentVisible);
}
