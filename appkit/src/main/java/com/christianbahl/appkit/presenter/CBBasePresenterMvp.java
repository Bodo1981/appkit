package com.christianbahl.appkit.presenter;

import com.christianbahl.appkit.view.CBBaseMvpView;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class CBBasePresenterMvp<D, V extends CBBaseMvpView<D>> extends CBBasePresenter<V> {

  protected void setData(List<D> data) {
    if (isViewAttached()) {
      getView().setData(data);
    }
  }

  protected void showLoading(boolean isContentVisible) {
    if (isViewAttached()) {
      getView().showLoading(isContentVisible);
    }
  }

  protected void showContent() {
    if (isViewAttached()) {
      getView().showContent();
    }
  }

  protected void showError(Exception e, boolean isContentVisible) {
    if (isViewAttached()) {
      getView().showError(e, isContentVisible);
    }
  }
}
