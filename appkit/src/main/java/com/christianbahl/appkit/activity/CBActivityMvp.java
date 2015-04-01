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
package com.christianbahl.appkit.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.christianbahl.appkit.R;
import com.christianbahl.appkit.presenter.CBBasePresenter;
import com.christianbahl.appkit.util.CBFadeHelper;
import com.christianbahl.appkit.view.CBMvpView;

/**
 * An activity which uses a {@link P} to implement the Model-View-Presenter architecture. The
 * {@link P} is responsible for loading and preparing the data for the view.
 *
 * <p>
 * To implement this architecture your activity must have a layout which contains the following
 * views:
 * <ul>
 * <li>contentView: <code>R.layout.content_view</code></li>
 * <li>errorView: <code>R.layout.error_view</code></li>
 * <li>loadingView: <code>R.layout.loading_view</code></li>
 * </ul>
 * </p>
 *
 * @author Christian Bahl
 * @see CBActivityPresenter
 */
public abstract class CBActivityMvp<CV extends View, D, V extends CBMvpView<D>, P extends CBBasePresenter<V>>
    extends CBActivityPresenter<P> implements CBMvpView<D> {

  protected CV contentView;
  protected TextView errorView;
  protected View loadingView;

  @SuppressWarnings("unchecked") @Override public void onSupportContentChanged() {
    super.onSupportContentChanged();

    contentView = (CV) findViewById(R.id.content_view);
    loadingView = findViewById(R.id.loading_view);
    errorView = (TextView) findViewById(R.id.error_view);

    if (contentView == null) {
      throw new IllegalStateException("The content view is not specified. "
          + "You have to provide a View with R.id.content_view in your inflated xml layout");
    }

    if (loadingView == null) {
      throw new IllegalStateException("The loading view is not specified. "
          + "You have to provide a View with R.id.loading_view in your inflated xml layout");
    }

    if (errorView == null) {
      throw new IllegalStateException("The error view is not specified. "
          + "You have to provide a View with R.id.error_view in your inflated xml layout");
    }

    errorView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onErrorViewClicked();
      }
    });
  }

  @Override public void showLoading(boolean isContentVisible) {
    animateLoadingViewIn(isContentVisible);
  }

  @Override public void showContent() {
    animateContentViewIn();
  }

  @Override public void showError(Exception e, boolean isContentVisible) {
    String errorMsg = getErrorMessage(e, isContentVisible);

    if (isContentVisible) {
      showLightError(errorMsg);
    } else {
      animateErrorViewIn(errorMsg);
    }
  }

  /**
   * Get the error message for a certain Exception that will be shown on {@link
   * #showError(Exception, boolean)}
   *
   * @param e {@link Exception}
   * @param isContentVisible is content visible
   */
  protected String getErrorMessage(Exception e, boolean isContentVisible) {
    return e.getLocalizedMessage();
  }

  /**
   * Show light error
   *
   * @param errorMsg error message
   */
  protected void showLightError(String errorMsg) {
    Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
  }

  /**
   * Animate the loading view
   *
   * @param isContentVisible is content visible
   */
  protected void animateLoadingViewIn(boolean isContentVisible) {
    if (!isContentVisible) {
      CBFadeHelper.showLoading(loadingView, contentView, errorView);
    }
  }

  /**
   * Animate the content view in
   */
  protected void animateContentViewIn() {
    CBFadeHelper.showContent(loadingView, contentView, errorView);
  }

  /**
   * Animate the error view in
   *
   * @param errorMsg error message
   */
  protected void animateErrorViewIn(String errorMsg) {
    CBFadeHelper.showError(errorMsg, loadingView, contentView, errorView);
  }

  /**
   * Called if the user clicks on the error view (R.id.error_view)
   */
  protected abstract void onErrorViewClicked();
}
