package com.christianbahl.appkit.sampleviewstate.activity_toolbar_tabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.christianbahl.appkit.viewstate.activity.CBActivityMvpToolbarTabsViewState;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarTabs extends
    CBActivityMvpToolbarTabsViewState<List<String>, MvpLceView<List<String>>, ActivityToolbarTabsPresenter, ActivityToolbarTabsAdapter> {

  private List<String> data;

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbarTabs.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @Override protected ActivityToolbarTabsAdapter createAdapter() {
    return new ActivityToolbarTabsAdapter(getSupportFragmentManager());
  }

  @Override public LceViewState<List<String>, MvpLceView<List<String>>> createViewState() {
    return new RetainingLceViewState<>();
  }

  @Override public List<String> getData() {
    return data;
  }

  @NonNull @Override public ActivityToolbarTabsPresenter createPresenter() {
    return new ActivityToolbarTabsPresenter();
  }

  @Override public void setData(List<String> data) {
    this.data = data;

    adapter.setItems(data);
    adapter.notifyDataSetChanged();
    tabs.setTabsFromPagerAdapter(adapter);
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }
}
