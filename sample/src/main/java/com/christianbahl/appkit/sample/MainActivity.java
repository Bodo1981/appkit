package com.christianbahl.appkit.sample;

import com.christianbahl.appkit.core.activity.CBActivityMvpToolbarTabs;
import com.christianbahl.appkit.core.view.CBMvpView;
import java.util.List;

public class MainActivity
    extends CBActivityMvpToolbarTabs<List<String>, CBMvpView<String>, MainPresenter, MainAdapter>
    implements CBMvpView<String> {

  @Override protected MainAdapter createAdapter() {
    return new MainAdapter(getSupportFragmentManager());
  }

  @Override public MainPresenter createPresenter() {
    return new MainPresenter();
  }

  @Override public void setData(List<String> data) {
    adapter.setTabs(data);
    adapter.notifyDataSetChanged();
    tabs.setTabsFromPagerAdapter(adapter);
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadMenu(pullToRefresh);
  }
}
