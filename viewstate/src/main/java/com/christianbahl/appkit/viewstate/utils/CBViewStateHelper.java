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

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.christianbahl.appkit.view.CBView;
import com.christianbahl.appkit.viewstate.data.interfaces.CBViewStateInterface;
import com.christianbahl.appkit.viewstate.data.interfaces.CBViewStateRestoreableInterface;

/**
 * @author Christian Bahl
 */
public class CBViewStateHelper {

  /**
   * Creates or restores the view state
   *
   * @param viewStateSupport {@link CBViewStateSupport}
   * @param view {@link V}
   * @param savedInstanceState saved instance state
   * @return <code>true</code> if the view state is restored successfully otherwise
   * <code>false</code> if a new view state has been created
   */
  public static <V extends CBView> boolean createOrRestoreViewState(
      @NonNull CBViewStateSupport<V> viewStateSupport, @NonNull V view, Bundle savedInstanceState) {
    if (viewStateSupport == null) {
      throw new NullPointerException("CBViewStateSupport can not be null");
    }

    if (view == null) {
      throw new NullPointerException("View can not be null");
    }

    // Fragments
    if (viewStateSupport.getViewState() != null) {
      viewStateSupport.setViewStateRestoring(true);
      viewStateSupport.getViewState().restore(view, true);
      viewStateSupport.setViewStateRestoring(false);
      viewStateSupport.onViewStateRestored(true);
      return true;
    }

    // Activities
    viewStateSupport.setViewState(viewStateSupport.createViewState());
    if (viewStateSupport.getViewState() == null) {
      throw new NullPointerException(
          "CBViewState is null! Do you return null in createViewState() method?");
    }

    if (savedInstanceState != null
        && viewStateSupport.getViewState() instanceof CBViewStateRestoreableInterface) {
      boolean restoredFromBundle =
          ((CBViewStateRestoreableInterface) viewStateSupport.getViewState()).restoreInstanceState(
              savedInstanceState);

      if (restoredFromBundle) {
        viewStateSupport.setViewStateRestoring(true);
        viewStateSupport.getViewState().restore(view, false);
        viewStateSupport.setViewStateRestoring(false);
        viewStateSupport.onViewStateRestored(false);
        return true;
      }
    }

    // Not restored
    viewStateSupport.onViewStateNew();
    return false;
  }

  public static void saveViewState(@NonNull CBViewStateSupport viewStateSupport, Bundle outState) {
    if (viewStateSupport == null) {
      throw new NullPointerException("CBViewStateSupport can not be null");
    }

    CBViewStateInterface viewState = viewStateSupport.getViewState();
    if (viewState == null) {
      throw new NullPointerException("CBViewState can not be null");
    }

    if (viewState instanceof CBViewStateRestoreableInterface) {
      ((CBViewStateRestoreableInterface) viewState).saveInstanceState(outState);
    }
  }
}
