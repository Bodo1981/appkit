package com.christianbahl.appkit.sampleviewstate.activity_toolbar_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.christianbahl.appkit.viewstate.activity.CBActivityMvpToolbarFragmentViewState;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarFragment extends
    CBActivityMvpToolbarFragmentViewState<FrameLayout, String, MvpLceView<String>, ActivityToolbarFragmentPresenter> {

  private String data;

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbarFragment.class);
  }

  @NonNull @Override protected Fragment createFragmentToDisplay() {
    return ActivityToolbarFragmentFragment.newInstance();
  }

  @NonNull @Override public LceViewState<String, MvpLceView<String>> createViewState() {
    return new RetainingLceViewState<>();
  }

  @Override public String getData() {
    return data;
  }

  @NonNull @Override public ActivityToolbarFragmentPresenter createPresenter() {
    return new ActivityToolbarFragmentPresenter();
  }

  @Override public void setData(String data) {
    this.data = data;
    Toast.makeText(this, data, Toast.LENGTH_LONG).show();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }
}
