package com.christianbahl.appkit.mvp.view;

import java.util.List;

/**
 * Created by cbahl on 17.01.15.
 */
public interface CBBaseView<D> {

  /**
   * Show loading view
   *
   * @param isContentVisible is content visible
   */
  public void showLoading(boolean isContentVisible);

  /**
   * Show content view
   */
  public void showContent();

  /**
   * Show error view
   *
   * @param e {@link Exception}
   * @param isContentVisible is content visible
   */
  public void showError(Exception e, boolean isContentVisible);

  /**
   * Set data
   *
   * @param data {@link List<D>}
   */
  public void setData(List<D> data);

}
