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
package com.christianbahl.appkit.viewstate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.christianbahl.appkit.fragment.CBFragmentPresenter;
import com.christianbahl.appkit.presenter.CBPresenterInterface;
import com.christianbahl.appkit.view.CBView;
import com.christianbahl.appkit.viewstate.data.interfaces.CBViewStateInterface;
import com.christianbahl.appkit.viewstate.utils.CBViewStateHelper;
import com.christianbahl.appkit.viewstate.utils.CBViewStateSupport;

/**
 * @author Christian Bahl
 */
public abstract class CBFragmentPresenterViewState<V extends CBView, P extends CBPresenterInterface<V>>
    extends CBFragmentPresenter<P> implements CBViewStateSupport<V> {

  private CBViewStateInterface<V> viewState;
  private boolean viewStateRestoring = false;

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    createOrRestoreViewState(savedInstanceState);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    saveViewState(outState);
  }

  /**
   * Creates or restores the {@link CBViewStateInterface}
   *
   * @param savedInstanceState saved instance state
   * @return <code>true</code> if {@link CBViewStateInterface} is restored successfully
   * otherwise
   * <code>false</code>
   */
  @SuppressWarnings("unchecked") protected boolean createOrRestoreViewState(
      Bundle savedInstanceState) {
    return CBViewStateHelper.createOrRestoreViewState(this, (V) this, savedInstanceState);
  }

  /**
   * Saves the {@link CBViewStateInterface}. <br />
   * Called in {@link #onSaveInstanceState(Bundle)}
   *
   * @param outState The bundle to store
   */
  protected void saveViewState(Bundle outState) {
    CBViewStateHelper.saveViewState(this, outState);
  }

  @Override public CBViewStateInterface<V> getViewState() {
    return viewState;
  }

  @Override public void setViewState(CBViewStateInterface<V> viewState) {
    this.viewState = viewState;
  }

  @Override public void setViewStateRestoring(boolean viewStateRestoring) {
    this.viewStateRestoring = viewStateRestoring;
  }

  @Override public boolean isViewStateRestoring() {
    return viewStateRestoring;
  }

  @Override public void onViewStateRestored(boolean instanceStateRetained) {
    // not needed here. can be overriden in subclasses
  }
}
