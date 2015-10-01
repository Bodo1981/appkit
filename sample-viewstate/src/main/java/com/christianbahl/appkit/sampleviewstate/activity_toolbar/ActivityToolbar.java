package com.christianbahl.appkit.sampleviewstate.activity_toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.christianbahl.appkit.viewstate.activity.CBActivityMvpToolbarViewState;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

/**
 * @author Christian Bahl
 */
public class ActivityToolbar extends
    CBActivityMvpToolbarViewState<FrameLayout, String, MvpLceView<String>, ActivityToolbarPresenter> {

  private String data;

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbar.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @Override public LceViewState<String, MvpLceView<String>> createViewState() {
    return new RetainingLceViewState<>();
  }

  @Override public String getData() {
    return data;
  }

  @NonNull @Override public ActivityToolbarPresenter createPresenter() {
    return new ActivityToolbarPresenter();
  }

  @Override public void setData(String data) {
    this.data = data;
    Toast.makeText(this, data, Toast.LENGTH_LONG).show();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }

  @Override public void onNewViewStateInstance() {
    super.onNewViewStateInstance();
  }
}
