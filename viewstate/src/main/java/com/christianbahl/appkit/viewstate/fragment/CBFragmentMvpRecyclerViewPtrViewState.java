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
package com.christianbahl.appkit.viewstate.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import com.christianbahl.appkit.core.adapter.CBAdapterRecyclerView;
import com.christianbahl.appkit.viewstate.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import java.util.List;

/**
 * @author Christian Bahl
 */
public abstract class CBFragmentMvpRecyclerViewPtrViewState<AD, D, V extends MvpLceView<D>, P extends MvpPresenter<V>, A extends CBAdapterRecyclerView<AD, List<AD>>>
    extends CBFragmentMvpRecyclerViewViewState<AD, D, V, P, A> {

  protected SwipeRefreshLayout swipeRefreshLayout;

  @Override protected int getLayoutRes() {
    return R.layout.cb_fragment_recycler_view_ptr;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.pullToRefresh);
    if (swipeRefreshLayout == null) {
      throw new IllegalStateException("The swipe refresh layout is not specified. "
          + "You have to provide a View with R.id.pull_to_refresh in your inflated xml layout");
    }

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        onRefreshStarted();
      }
    });
  }

  @Override public void showContent() {
    super.showContent();

    swipeRefreshLayout.setVisibility(View.VISIBLE);
    swipeRefreshLayout.setRefreshing(false);
  }

  @Override public void showLoading(boolean isContentVisible) {
    super.showLoading(isContentVisible);

    if (!isContentVisible) {
      swipeRefreshLayout.setVisibility(View.GONE);
    }
  }

  @Override public void showError(Throwable e, boolean isContentVisible) {
    super.showError(e, isContentVisible);

    swipeRefreshLayout.setVisibility(View.GONE);
    swipeRefreshLayout.setRefreshing(false);
  }

  /**
   * Called from the {@link SwipeRefreshLayout.OnRefreshListener}.<br>
   * Default: call of {@link #loadData(boolean)}
   */
  protected void onRefreshStarted() {
    loadData(true);
  }
}
