package com.christianbahl.appkit.templates.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.christianbahl.appkit.mvp.activity.CBBaseActivityMvp;
import com.christianbahl.appkit.mvp.presenter.CBBasePresenter;
import com.christianbahl.appkit.mvp.view.CBBaseView;
import com.christianbahl.appkit.templates.R;

/**
 * Created by cbahl on 17.01.15.
 */
public abstract class CBBaseActivityMvpToolbarFragment<CV extends View, D, V extends CBBaseView<D>, P extends CBBasePresenter<V, D>>
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
    return R.layout.activity_toolbar_fragment;
  }

  /**
   * Returns the {@link Fragment} which should be displayed by this activity.
   *
   * @return {@link Fragment}
   */
  protected abstract Fragment getFragmentToDisplay();
}
