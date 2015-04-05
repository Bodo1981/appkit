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
package com.christianbahl.appkit.viewstate.data;

import com.christianbahl.appkit.view.CBMvpView;

/**
 * @author Christian Bahl
 */
public class CBViewStateMvp<D, V extends CBMvpView<D>> implements CBViewStateMvpInterface<D, V> {

  protected int currentViewState;
  protected boolean contentVisible;
  protected Exception exception;
  protected D loadedData;

  @Override public void setViewStateShowLoading(boolean isContentVisible) {
    this.currentViewState = VIEW_STATE_SHOW_LOADING;
    this.contentVisible = isContentVisible;
    this.exception = null;

    // clear loaded data if content was not visible (e.g. first start)
    if (!isContentVisible) {
      this.loadedData = null;
    }
  }

  @Override public void setViewStateShowContent(D loadedData) {
    this.currentViewState = VIEW_STATE_SHOW_CONTENT;
    this.contentVisible = true;
    this.loadedData = loadedData;
    this.exception = null;
  }

  @Override public void setViewStateShowError(Throwable e, boolean isContentVisible) {
    this.currentViewState = VIEW_STATE_SHOW_ERROR;
    this.contentVisible = isContentVisible;
    this.exception = null;

    // clear loaded data if content was not visible (e.g. first start)
    if (!isContentVisible) {
      this.loadedData = null;
    }
  }

  @Override public void restore(V view, boolean retained) {
    if (currentViewState == VIEW_STATE_SHOW_LOADING) {
      // if content was visible before set it again to the view
      if (contentVisible) {
        view.setData(loadedData);
        view.showContent();
      }

      // if retained (only fragments) show loading else (activity) reload data
      if (retained) {
        view.showLoading(contentVisible);
      } else {
        view.loadData(contentVisible);
      }
    } else if (currentViewState == VIEW_STATE_SHOW_CONTENT) {
      view.setData(loadedData);
      view.showContent();
    } else if (currentViewState == VIEW_STATE_SHOW_ERROR) {
      // if content was visible before set it again to the view
      if (contentVisible) {
        view.setData(loadedData);
        view.showContent();
      }

      view.showError(exception, contentVisible);
    }
  }
}
