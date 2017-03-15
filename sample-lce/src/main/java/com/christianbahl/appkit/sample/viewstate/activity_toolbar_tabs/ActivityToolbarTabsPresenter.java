package com.christianbahl.appkit.sample.viewstate.activity_toolbar_tabs;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarTabsPresenter extends MvpBasePresenter<MvpLceView<List<String>>> {

  public void loadData(boolean contentPresent) {
    if (isViewAttached()) {
      getView().showLoading(contentPresent);
    }

    if (new Random().nextInt(10) % 3 == 0) {
      if (isViewAttached()) {
        getView().showError(new NullPointerException("No Data found!"), contentPresent);
      }
    } else {
      if (isViewAttached()) {
        getView().setData(getItems());
        getView().showContent();
      }
    }
  }

  private List<String> getItems() {
    List<String> items = new ArrayList<>();
    items.add("Fragment 1");
    items.add("Fragment 2");
    items.add("Fragment 3");
    items.add("Fragment 4");

    return items;
  }
}
