/*
 * Copyright 2015 Christian Bahl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.christianbahl.appkit.view;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * A base view for the Model-View-Presenter architecture with show loading / content / error and
 * set data.
 *
 * @param <D> data which you bind to the {@link Activity} / {@link Fragment}
 * @author Christian Bahl
 */
public interface CBMvpView<D> extends CBView {

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
   * @param data {@link D}
   */
  void setData(D data);
}
