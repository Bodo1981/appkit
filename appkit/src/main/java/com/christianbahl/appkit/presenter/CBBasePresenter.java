package com.christianbahl.appkit.presenter;

import com.christianbahl.appkit.view.CBBaseView;
import java.lang.ref.WeakReference;

/**
 * Created by cbahl on 17.01.15.
 */
public class CBBasePresenter<V extends CBBaseView> implements CBIBasePresenter<V> {

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
    return viewReference != null && viewReference.get() != null;
  }

  @Override public void setView(V view) {
    viewReference = new WeakReference<>(view);
  }

  @Override public void onDestroy(boolean retainInstanceState) {
    if (isViewAttached()) {
      viewReference.clear();
      viewReference = null;
    }
  }
}
