package com.christianbahl.appkit.viewstate.activity;

import android.os.Bundle;
import com.christianbahl.appkit.activity.CBActivityPresenter;
import com.christianbahl.appkit.presenter.CBPresenterInterface;
import com.christianbahl.appkit.view.CBView;
import com.christianbahl.appkit.viewstate.data.interfaces.CBViewStateInterface;
import com.christianbahl.appkit.viewstate.data.interfaces.CBViewStateRestoreableInterface;
import com.christianbahl.appkit.viewstate.utils.CBViewStateHelper;
import com.christianbahl.appkit.viewstate.utils.CBViewStateSupport;

/**
 * @author Christian Bahl
 */
public abstract class CBActivityPresenterViewState<V extends CBView, P extends CBPresenterInterface<V>>
    extends CBActivityPresenter<P> implements CBViewStateSupport<V> {

  private CBViewStateRestoreableInterface<V> viewState;
  private boolean viewStateRestoring = false;

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

  @Override public CBViewStateRestoreableInterface<V> getViewState() {
    return viewState;
  }

  @SuppressWarnings("unchecked") @Override
  public void setViewState(CBViewStateInterface<V> viewState) {
    if (!(viewState instanceof CBViewStateRestoreableInterface)) {
      throw new IllegalArgumentException("View state must be of subclass "
          + CBViewStateRestoreableInterface.class.getCanonicalName());
    }

    this.viewState = (CBViewStateRestoreableInterface) viewState;
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

  @SuppressWarnings("unchecked") @Override
  public abstract CBViewStateRestoreableInterface createViewState();
}
