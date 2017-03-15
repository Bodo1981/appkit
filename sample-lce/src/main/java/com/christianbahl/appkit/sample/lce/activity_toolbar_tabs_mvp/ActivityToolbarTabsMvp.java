package com.christianbahl.appkit.sample.lce.activity_toolbar_tabs_mvp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.christianbahl.appkit.lce.activity.CBActivityLceToolbarTabs;
import com.christianbahl.appkit.lce.view.CBLceView;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarTabsMvp
    extends CBActivityLceToolbarTabs<List<String>, CBLceView<String>, ActivityToolbarTapsMvpPresenter, ActivityToolbarTabsMvpAdapter> {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbarTabsMvp.class);
  }

  @NonNull @Override protected ActivityToolbarTabsMvpAdapter createAdapter() {
    return new ActivityToolbarTabsMvpAdapter(getSupportFragmentManager());
  }

  @NonNull @Override public ActivityToolbarTapsMvpPresenter createPresenter() {
    return new ActivityToolbarTapsMvpPresenter();
  }

  @Override public void setData(List<String> data) {
    adapter.setItems(data);
    adapter.notifyDataSetChanged();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }
}
