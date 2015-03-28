package com.christianbahl.appkit.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import com.christianbahl.appkit.R;
import com.christianbahl.appkit.presenter.CBBasePresenter;
import com.christianbahl.appkit.view.CBBaseMvpView;

/**
 * Created by cbahl on 17.01.15.
 */
public abstract class CBBaseActivityMvpToolbar<CV extends View, D, V extends CBBaseMvpView<D>, P extends CBBasePresenter<V>>
    extends CBBaseActivityMvp<CV, D, V, P> {

  protected Toolbar toolbar;

  @Override public void onSupportContentChanged() {
    super.onSupportContentChanged();

    toolbar = (Toolbar) findViewById(R.id.toolbar);

    if (toolbar == null) {
      throw new IllegalStateException("The toolbar is not specified. "
          + "You have to provide a View with R.id.toolbar in your inflated xml layout");
    }

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(true);
  }
}
