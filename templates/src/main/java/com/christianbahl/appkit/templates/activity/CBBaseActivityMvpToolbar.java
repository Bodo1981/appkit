package com.christianbahl.appkit.templates.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.christianbahl.appkit.mvp.activity.CBBaseActivityMvp;
import com.christianbahl.appkit.mvp.presenter.CBBasePresenter;
import com.christianbahl.appkit.mvp.view.CBBaseView;
import com.christianbahl.appkit.templates.R;

/**
 * Created by cbahl on 17.01.15.
 */
public abstract class CBBaseActivityMvpToolbar<CV extends View, D, V extends CBBaseView<D>, P extends CBBasePresenter<V, D>>
    extends CBBaseActivityMvp<CV, D, V, P> {

  protected Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(true);
  }

  @Override protected void injectViews() {
    super.injectViews();

    toolbar = (Toolbar) findViewById(R.id.toolbar);

    if (toolbar == null) {
      throw new IllegalStateException("The toolbar is not specified. "
          + "You have to provide a View with R.id.toolbar in your inflated xml layout");
    }
  }
}
