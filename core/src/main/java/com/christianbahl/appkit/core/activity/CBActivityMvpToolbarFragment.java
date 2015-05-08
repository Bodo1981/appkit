package com.christianbahl.appkit.core.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import com.christianbahl.appkit.core.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * An activity which uses the Model-View-Presenter architecture. It also adds a
 * {@link Toolbar} on top and has a container for the [Fragment].
 *
 * The layout must have a {@link ViewGroup} for the {@link Fragment} with the id
 * `R.layout.contentView`
 *
 * The standard layout implements all necessary views. You can override the default layout in
 * {@link #getLayoutRes}. But be careful, you have to provide the necessary views!
 *
 * You have to override the {@link #createFragmentToDisplay} to create the {@link Fragment} which
 * should be displayed.
 *
 * @author Christian Bahl
 * @see CBActivityMvpToolbar
 */
public abstract class CBActivityMvpToolbarFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends CBActivityMvpToolbar<CV, M, V, P> {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.contentView, createFragmentToDisplay())
          .commit();
    }
  }

  protected Integer getLayoutRes() {
    return R.layout.cb_activity_toolbar_fragment;
  }

  /**
   * Returns the {@link Fragment} which should be displayed by this activity.
   *
   * @return {@link Fragment}
   */
  protected abstract Fragment createFragmentToDisplay();
}
