package com.christianbahl.appkit.samplecore.activity_toolbar_fragment_mvp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.christianbahl.appkit.core.activity.CBActivityMvpToolbarFragment;
import com.christianbahl.appkit.samplecore.activity_fragment.FragmentToDisplay;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarFragmentMvp extends CBActivityMvpToolbarFragment<FrameLayout, String, MvpLceView<String>, ActivityToolbarFragmentMvpPresenter> {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbarFragmentMvp.class);
  }

  @Override protected Fragment createFragmentToDisplay() {
    return FragmentToDisplay.newInstance();
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    getSupportActionBar().setTitle("Activity with Toolbar and Fragment (MVP)");
  }

  @NonNull @Override public ActivityToolbarFragmentMvpPresenter createPresenter() {
    return new ActivityToolbarFragmentMvpPresenter();
  }

  @Override public void setData(String data) {
    Toast.makeText(this, data, Toast.LENGTH_LONG).show();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }
}
