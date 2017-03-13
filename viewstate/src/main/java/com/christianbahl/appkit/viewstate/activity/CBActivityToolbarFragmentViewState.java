package com.christianbahl.appkit.viewstate.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

/**
 * @author Christian Bahl
 */
public abstract class CBActivityToolbarFragmentViewState<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
    extends CBActivityToolbarViewState<V, P, VS> {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState == null) {
      Fragment fragment = createFragmentToDisplay();

      if (fragment == null) {
        throw new NullPointerException("Fragment is null. Did you return null in createFragmentToDisplay()?");
      }

      getSupportFragmentManager().beginTransaction().replace(com.christianbahl.appkit.core.R.id.contentView, fragment).commit();
    }
  }

  @Override @NonNull protected Integer getLayoutRes() {
    return com.christianbahl.appkit.core.R.layout.cb_activity_toolbar_fragment;
  }

  /**
   * Returns the {@link Fragment} which should be displayed by this activity.
   *
   * @return {@link Fragment}
   */
  @NonNull protected abstract Fragment createFragmentToDisplay();
}
