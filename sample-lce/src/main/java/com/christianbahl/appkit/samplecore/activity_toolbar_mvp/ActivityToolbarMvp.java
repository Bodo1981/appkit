package com.christianbahl.appkit.samplecore.activity_toolbar_mvp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.christianbahl.appkit.lce.activity.CBActivityLceToolbar;
import com.christianbahl.appkit.samplecore.common.StringPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarMvp extends CBActivityLceToolbar<FrameLayout, String, MvpLceView<String>, StringPresenter> {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbarMvp.class);
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    getSupportActionBar().setTitle("Activity with Toolbar (MVP)");
  }

  @NonNull @Override public StringPresenter createPresenter() {
    return new StringPresenter();
  }

  @Override public void setData(String data) {
    Toast.makeText(this, data, Toast.LENGTH_LONG).show();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }
}
