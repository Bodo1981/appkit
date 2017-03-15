package com.christianbahl.appkit.sample.viewstate.activity_toolbar_tabs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.christianbahl.appkit.lce.view.CBLceView;
import com.christianbahl.appkit.lce.viewstate.activity.CBActivityLceToolbarTabsViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarTabs
    extends CBActivityLceToolbarTabsViewState<List<String>, CBLceView<String>, ActivityToolbarTabsPresenter, ActivityToolbarTabsAdapter> {

  private List<String> data;

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbarTabs.class);
  }

  @NonNull @Override protected ActivityToolbarTabsAdapter createAdapter() {
    return new ActivityToolbarTabsAdapter(getSupportFragmentManager());
  }

  @NonNull @Override public LceViewState<List<String>, CBLceView<String>> createViewState() {
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
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }
}
