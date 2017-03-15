package com.christianbahl.appkit.sample.viewstate.activity_toolbar;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import java.util.Random;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarPresenter extends MvpBasePresenter<MvpLceView<String>> {

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
        getView().setData("Activity Toolbar (Viewstate) data loaded");
        getView().showContent();
      }
    }
  }
}
