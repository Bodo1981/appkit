package com.christianbahl.appkit.sampleviewstate.fragment_recyclerview_list;

import com.christianbahl.appkit.core.common.view.CBMvpView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewListPresenter extends MvpBasePresenter<CBMvpView<String>> {

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
