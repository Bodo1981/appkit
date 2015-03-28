package com.christianbahl.appkit.fragment;

import android.os.Bundle;
import android.view.View;
import com.christianbahl.appkit.presenter.CBBasePresenter;
import com.christianbahl.appkit.view.CBBaseView;

/**
 * @author Christian Bahl
 */
public abstract class CBBaseFragmentPresenter<P extends CBBasePresenter> extends CBBaseFragment
    implements CBBaseView {

  protected P presenter;

  @SuppressWarnings("unchecked") @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (presenter == null) {
      presenter = createPresenter();

      if (presenter == null) {
        throw new NullPointerException(
            "Presenter is null! Did you forget to return it in createPresenter()?");
      }
    }

    presenter.setView(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();

    if (presenter != null) {
      presenter.onDestroy(getRetainInstance());
    }
  }

  /**
   * Creates the {@link P}. <br>
   * Called in {@link #onViewCreated(View, Bundle)}
   *
   * @return {@link P}
   */
  protected abstract P createPresenter();
}
