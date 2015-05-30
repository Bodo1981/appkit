package com.christianbahl.appkit.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class MainAdapter extends FragmentPagerAdapter {

  private List<String> tabs;

  public MainAdapter(FragmentManager fm) {
    super(fm);
  }

  public void setTabs(List<String> tabs) {
    this.tabs = tabs;
  }

  @Override public Fragment getItem(int position) {
    return new MainFragment();
  }

  @Override public int getCount() {
    return tabs == null ? 0 : tabs.size();
  }

  @Override public CharSequence getPageTitle(int position) {
    return tabs.get(position);
  }
}
