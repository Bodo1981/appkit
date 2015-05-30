package com.christianbahl.appkit.sample;

import com.christianbahl.appkit.core.view.CBMvpView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class MainPresenter extends MvpBasePresenter<CBMvpView<String>> {

  public void loadMenu(boolean isContentVisible) {
    if (isViewAttached()) {
      getView().showLoading(isContentVisible);
    }

    List<String> tabs = new ArrayList<>(4);
    tabs.add("Tab 1");
    tabs.add("Tab 2");
    tabs.add("Tab 3");
    tabs.add("Tab 4");
    tabs.add("Tab 5");
    tabs.add("Tab 6");
    tabs.add("Tab 7");
    tabs.add("Tab 8");

    if (isViewAttached()) {
      getView().setData(tabs);
      getView().showContent();
    }
  }
}
