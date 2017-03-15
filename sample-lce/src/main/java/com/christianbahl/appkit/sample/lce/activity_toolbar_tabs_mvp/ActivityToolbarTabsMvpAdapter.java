package com.christianbahl.appkit.sample.lce.activity_toolbar_tabs_mvp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarTabsMvpAdapter extends FragmentPagerAdapter {

  private List<String> items;

  public ActivityToolbarTabsMvpAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    return ActivityToolbarTabsMvpFragment.newInstance(items.get(position));
  }

  @Override public int getCount() {
    return items == null ? 0 : items.size();
  }

  @Override public CharSequence getPageTitle(int position) {
    return items.get(position);
  }

  public void setItems(List<String> items) {
    this.items = items;
  }
}

