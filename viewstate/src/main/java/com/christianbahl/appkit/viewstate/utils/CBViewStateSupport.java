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
package com.christianbahl.appkit.viewstate.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.view.CBMvpView;
import com.christianbahl.appkit.viewstate.CBViewStateInterface;

/**
 * @author Christian Bahl
 */
public interface CBViewStateSupport<V extends CBMvpView> {

  /**
   * Returns the view state
   *
   * @return {@link CBViewStateInterface}
   */
  CBViewStateInterface<V> getViewState();

  /**
   * Sets the view state
   *
   * @param viewState {@link CBViewStateInterface}
   */
  void setViewState(CBViewStateInterface<V> viewState);

  /**
   * Creates the view state
   *
   * @return {@link CBViewStateInterface}
   */
  CBViewStateInterface<V> createViewState();

  /**
   * Sets the flag that the {@link CBViewStateInterface} is restoring or not
   *
   * @param viewStateRestoring view state is restoring
   */
  void setViewStateRestoring(boolean viewStateRestoring);

  /**
   * Gets the info if the view state is restoring at the moment
   *
   * @return <code>true</code> if the view state is restoring at the moment otherwise
   * <code>false</code>
   */
  boolean isViewStateRestoring();

  /**
   * Called after the view state has been restored successfully
   *
   * @param instanceStateRetained <code>true</code> if the view state has been retained in {@link
   * Fragment} by {@link Fragment#setRetainInstance(boolean)} otherwise <code>false</code> for
   * {@link Activity}
   */
  void onViewStateRestored(boolean instanceStateRetained);

  /**
   * Called if a new view state is created because no view state from the activity / fragment could
   * be restored.
   *
   * <p>
   * Normally this is called the first time an activity / fragment is created because at that
   * moment
   * there is no view state which could be restored.
   * </p>
   */
  void onViewStateNew();
}
