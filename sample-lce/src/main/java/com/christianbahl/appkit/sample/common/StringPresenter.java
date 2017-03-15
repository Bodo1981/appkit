package com.christianbahl.appkit.sample.common;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import java.util.Random;

/**
 * @author Christian Bahl
 */
public class StringPresenter extends MvpBasePresenter<MvpLceView<String>> {

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
        getView().setData("Activity Toolbar Mvp data loaded");
        getView().showContent();
      }
    }
  }
}
