package com.christianbahl.appkit.sample;

import com.christianbahl.appkit.core.view.CBMvpView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class ListPresenter extends MvpBasePresenter<CBMvpView<String>> {

  public void loadList(boolean isContentVisible) {
    if (isViewAttached()) {
      getView().showLoading(isContentVisible);
    }

    List<String> stringList = new ArrayList<>(50);

    for (int i = 0; i <= 50; i++) {
      stringList.add("Item " + i);
    }

    if (isViewAttached()) {
      getView().setData(null);
      getView().showContent();
      //getView().showError(new NullPointerException("Fuck"), isContentVisible);
    }
  }
}
