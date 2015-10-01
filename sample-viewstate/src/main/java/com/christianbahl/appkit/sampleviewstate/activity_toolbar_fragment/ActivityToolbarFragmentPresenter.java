package com.christianbahl.appkit.sampleviewstate.activity_toolbar_fragment;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import java.util.Random;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarFragmentPresenter extends MvpBasePresenter<MvpLceView<String>> {

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
        getView().setData("Activity Toolbar Fragment (Viewstate) data loaded");
        getView().showContent();
      }
    }
  }
}
