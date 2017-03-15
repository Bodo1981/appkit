package com.christianbahl.appkit.sample.viewstate.activity_toolbar_tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarTabsAdapter extends FragmentStatePagerAdapter {

  private List<String> items;

  public ActivityToolbarTabsAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    return ActivityToolbarTabsFragment.newInstance(items.get(position));
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

