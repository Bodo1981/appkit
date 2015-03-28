package com.christianbahl.appkit.lib.activity;

import android.os.Bundle;
import com.christianbahl.appkit.lib.presenter.CBBasePresenter;
import com.christianbahl.appkit.lib.view.CBBaseView;

/**
 * Created by cbahl on 28.03.15.
 */
public abstract class CBBaseActivityPresenter<P extends CBBasePresenter> extends CBBaseActivity
    implements CBBaseView {

  protected P presenter;

  @SuppressWarnings("unchecked") @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    presenter = createPresenter();
    presenter.setView(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    if (presenter != null) {
      presenter.onDestroy(false);
    }
  }

  /**
   * Creates the {@link P} instance. <br />
   * Called in {@link #onCreate(Bundle)}
   *
   * @return {@link P}
   */
  protected abstract P createPresenter();
}
