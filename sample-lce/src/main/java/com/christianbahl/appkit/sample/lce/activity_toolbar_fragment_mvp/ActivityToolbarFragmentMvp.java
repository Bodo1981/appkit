package com.christianbahl.appkit.sample.lce.activity_toolbar_fragment_mvp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.christianbahl.appkit.lce.activity.CBActivityLceToolbarFragment;
import com.christianbahl.appkit.sample.lce.activity_fragment.FragmentToDisplay;
import com.christianbahl.appkit.sample.lce.common.StringPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarFragmentMvp extends CBActivityLceToolbarFragment<FrameLayout, String, MvpLceView<String>, StringPresenter> {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbarFragmentMvp.class);
  }

  @NonNull @Override protected Fragment createFragmentToDisplay() {
    return FragmentToDisplay.newInstance();
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    getSupportActionBar().setTitle("Activity with Toolbar and Fragment (MVP)");
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
