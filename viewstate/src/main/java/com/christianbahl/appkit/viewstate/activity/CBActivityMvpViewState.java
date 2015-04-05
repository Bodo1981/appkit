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
package com.christianbahl.appkit.viewstate.activity;

import android.os.Bundle;
import android.view.View;
import com.christianbahl.appkit.activity.CBActivityMvp;
import com.christianbahl.appkit.presenter.CBPresenterInterface;
import com.christianbahl.appkit.view.CBMvpView;
import com.christianbahl.appkit.viewstate.data.interfaces.CBViewStateInterface;
import com.christianbahl.appkit.viewstate.data.interfaces.CBViewStateMvpRestoreableParcelableInterface;
import com.christianbahl.appkit.viewstate.data.interfaces.CBViewStateRestoreableInterface;
import com.christianbahl.appkit.viewstate.utils.CBViewStateHelper;
import com.christianbahl.appkit.viewstate.utils.CBViewStateSupport;

/**
 * @author Christian Bahl
 */
public abstract class CBActivityMvpViewState<CV extends View, D, V extends CBMvpView<D>, P extends CBPresenterInterface<V>>
    extends CBActivityMvp<CV, D, V, P> implements CBViewStateSupport<V> {

  protected CBViewStateMvpRestoreableParcelableInterface<D, V> viewState;
  protected boolean viewStateRestoring = false;

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    createOrRestoreViewState(savedInstanceState);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    saveViewState(outState);
  }

  /**
   * Creates or restores the {@link CBViewStateRestoreableInterface}
   *
   * @param savedInstanceState saved instance state
   * @return <code>true</code> if {@link CBViewStateRestoreableInterface} is restored successfully
   * otherwise
   * <code>false</code>
   */
  @SuppressWarnings("unchecked") protected boolean createOrRestoreViewState(
      Bundle savedInstanceState) {
    return CBViewStateHelper.createOrRestoreViewState(this, (V) this, savedInstanceState);
  }

  /**
   * Saves the {@link CBViewStateRestoreableInterface}. <br />
   * Called in {@link #onSaveInstanceState(Bundle)}
   *
   * @param outState The bundle to store
   */
  protected void saveViewState(Bundle outState) {
    CBViewStateHelper.saveViewState(this, outState);
  }

  @Override public CBViewStateMvpRestoreableParcelableInterface<D, V> getViewState() {
    return viewState;
  }

  @SuppressWarnings("unchecked") @Override
  public void setViewState(CBViewStateInterface<V> viewState) {
    if (!(viewState instanceof CBViewStateMvpRestoreableParcelableInterface)) {
      throw new IllegalArgumentException("View state must be of subclass "
          + CBViewStateMvpRestoreableParcelableInterface.class.getCanonicalName());
    }

    this.viewState = (CBViewStateMvpRestoreableParcelableInterface) viewState;
  }

  @Override public void setViewStateRestoring(boolean viewStateRestoring) {
    this.viewStateRestoring = viewStateRestoring;
  }

  @Override public boolean isViewStateRestoring() {
    return viewStateRestoring;
  }

  @Override public void onViewStateNew() {
    loadData(false);
  }

  @Override public void onViewStateRestored(boolean instanceStateRetained) {
    // not needed here. can be overriden in subclasses
  }

  @Override public void showLoading(boolean isContentVisible) {
    super.showLoading(isContentVisible);

    viewState.setViewStateShowLoading(isContentVisible);
  }

  @Override public void showContent() {
    super.showContent();

    viewState.setViewStateShowContent(getData());
  }

  @Override public void showError(Exception e, boolean isContentVisible) {
    super.showError(e, isContentVisible);

    viewState.setViewStateShowError(e, isContentVisible);
  }

  @Override protected void showLightError(String errorMsg) {
    if (isViewStateRestoring()) {
      return;
    }

    super.showLightError(errorMsg);
  }

  /**
   * Creates the view state
   *
   * @return {@link CBViewStateMvpRestoreableParcelableInterface}
   */
  @Override public abstract CBViewStateMvpRestoreableParcelableInterface<D, V> createViewState();

  /**
   * Get the loaded data
   *
   * @return {@link D}
   */
  public abstract D getData();
}
