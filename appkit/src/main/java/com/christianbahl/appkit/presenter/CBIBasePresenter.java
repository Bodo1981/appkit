package com.christianbahl.appkit.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.view.CBBaseView;

/**
 * Created by cbahl on 28.03.15.
 */
public interface CBIBasePresenter<V extends CBBaseView> {

  /**
   * Attaches the {@link V} to the presenter
   *
   * @param view {@link V}
   */
  void setView(V view);

  /**
   * Called if the {@link Activity} / {@link Fragment} gets destroyed.
   *
   * @param retainInstanceState true if this presenter instance will be retained (i.e. durring
   * orientation changes) and will not be new instantiated, otherwise false. You may have to cancel
   * any running background threads, if retainInstanceState == false.
   */
  void onDestroy(boolean retainInstanceState);
}
