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
import android.view.View;
import com.christianbahl.appkit.fragment.CBFragmentMvp;
import com.christianbahl.appkit.presenter.CBPresenterInterface;
import com.christianbahl.appkit.view.CBMvpView;
import com.christianbahl.appkit.viewstate.CBViewStateInterface;
import com.christianbahl.appkit.viewstate.CBViewStateRestoreable;
import com.christianbahl.appkit.viewstate.data.CBViewStateMvpInterface;
import com.christianbahl.appkit.viewstate.utils.CBViewStateHelper;
import com.christianbahl.appkit.viewstate.utils.CBViewStateSupport;

/**
 * @author Christian Bahl
 */
public abstract class CBFragmentMvpViewState<CV extends View, D, V extends CBMvpView<D>, P extends CBPresenterInterface<V>>
    extends CBFragmentMvp<CV, D, V, P> implements CBViewStateSupport<V> {

  private CBViewStateMvpInterface<D, V> viewState;
  private boolean viewStateRestoring = false;

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    createOrRestoreViewState(savedInstanceState);
  }

  @Override public void onSaveInstanceState(Bundle out) {
    super.onSaveInstanceState(out);

    saveViewState(out);
  }

  /**
   * Creates or restores the {@link CBViewStateInterface}
   *
   * @param savedInstanceState saved instance state
   * @return <code>true</code> if {@link CBViewStateRestoreable} is restored successfully otherwise
   * <code>false</code>
   */
  @SuppressWarnings("unchecked") protected boolean createOrRestoreViewState(
      Bundle savedInstanceState) {
    return CBViewStateHelper.createOrRestoreViewState(this, (V) this, savedInstanceState);
  }

  /**
   * Saves the {@link CBViewStateRestoreable}. <br />
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
    this.viewState = (CBViewStateMvpInterface<D, V>) viewState;
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

  @Override public void onViewStateNew() {
    // not needed here. can be overriden in subclasses
  }
}
