package com.christianbahl.appkit.lib.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.christianbahl.appkit.lib.R;
import com.christianbahl.appkit.lib.presenter.CBBasePresenter;
import com.christianbahl.appkit.lib.view.CBBaseMvpView;

/**
 * Created by cbahl on 17.01.15.
 */
public abstract class CBBaseActivityMvpToolbarFragment<CV extends View, D, V extends CBBaseMvpView<D>, P extends CBBasePresenter<V>>
    extends CBBaseActivityMvp<CV, D, V, P> {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState == null) {
      Fragment fragment = getFragmentToDisplay();

      if (fragment != null) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.content_view, fragment)
            .commit();
      }
    }
  }

  @Override protected Integer getLayoutResId() {
    return R.layout.cb_activity_toolbar_fragment;
  }

  /**
   * Returns the {@link Fragment} which should be displayed by this activity.
   *
   * @return {@link Fragment}
   */
  protected abstract Fragment getFragmentToDisplay();
}
