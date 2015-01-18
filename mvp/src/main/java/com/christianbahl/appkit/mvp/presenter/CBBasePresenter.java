package com.christianbahl.appkit.mvp.presenter;

import com.christianbahl.appkit.mvp.view.CBBaseView;
import java.lang.ref.WeakReference;

/**
 * Created by cbahl on 17.01.15.
 */
public class CBBasePresenter<V extends CBBaseView<D>, D> {

  protected WeakReference<V> viewReference;

  public CBBasePresenter() {

  }

  public CBBasePresenter(V view) {
    setView(view);
  }

  protected V getView() {
    if (viewReference != null) {
      return viewReference.get();
    }

    return null;
  }

  public boolean isViewAttached() {
    return getView() != null;
  }

  public void setView(V view) {
    viewReference = new WeakReference<>(view);
  }

  public void onDestroy(boolean retainInstanceState) {
    viewReference = null;
  }
}
