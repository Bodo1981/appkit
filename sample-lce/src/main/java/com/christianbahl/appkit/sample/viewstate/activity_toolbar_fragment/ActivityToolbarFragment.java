package com.christianbahl.appkit.sample.viewstate.activity_toolbar_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.christianbahl.appkit.lce.viewstate.activity.CBActivityLceToolbarFragmentViewState;
import com.christianbahl.appkit.sample.common.StringPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarFragment
    extends CBActivityLceToolbarFragmentViewState<FrameLayout, String, MvpLceView<String>, StringPresenter> {

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

  @NonNull @Override public StringPresenter createPresenter() {
    return new StringPresenter();
  }

  @Override public void setData(String data) {
    this.data = data;
    Toast.makeText(this, data, Toast.LENGTH_LONG).show();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }
}
