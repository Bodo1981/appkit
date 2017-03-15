package com.christianbahl.appkit.sample.common;

import com.christianbahl.appkit.lce.view.CBLceView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bodo on 15.03.17.
 */

public class StringListPresenter extends MvpBasePresenter<CBLceView<String>> {

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

    for (int i = 1; i <= 30; i++) {
      items.add("Item " + i);
    }

    return items;
  }
}
