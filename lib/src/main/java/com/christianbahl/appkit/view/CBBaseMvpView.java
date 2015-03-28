package com.christianbahl.appkit.view;

import android.app.Activity;
import android.support.v4.app.Fragment;
import java.util.List;

/**
 * Base mvp view
 *
 * @param <D> data which you bind to the {@link Activity} / {@link Fragment}
 * @author Christian Bahl
 */
public interface CBBaseMvpView<D> extends CBBaseView {

  /**
   * Show loading view
   *
   * @param isContentVisible is content visible
   */
  void showLoading(boolean isContentVisible);

  /**
   * Show content view
   */
  void showContent();

  /**
   * Show error view
   *
   * @param e {@link Exception}
   * @param isContentVisible is content visible
   */
  void showError(Exception e, boolean isContentVisible);

  /**
   * Set data
   *
   * @param data {@link List<D>}
   */
  void setData(List<D> data);
}
