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
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.christianbahl.appkit.core.fragment.CBFragmentMvpRecyclerView;
import com.christianbahl.appkit.viewstate.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * <p>
 * A fragment which uses the Model-View-Presenter architecture with {@link ViewState} support.
 * </p>
 *
 * <p>
 * You have to specify a {@link SwipeRefreshLayout} with the id <code>R.layout.pullToRefresh</code>.
 * After the refresh is started the function {@link #onRefreshStarted()} is called. In the default
 * implementation {@link #loadData(boolean)} is called but you can override this if you need to.
 * </p>
 *
 * @author Christian Bahl
 * @see CBFragmentMvpRecyclerView
 */
public abstract class CBFragmentMvpRecyclerViewPtrViewState<M, V extends MvpLceView<M>, P extends MvpPresenter<V>, A extends RecyclerView.Adapter>
    extends CBFragmentMvpRecyclerViewViewState<M, V, P, A> {

  protected SwipeRefreshLayout swipeRefreshLayout;

  @Override protected Integer getLayoutRes() {
    return R.layout.cb_fragment_recycler_view_ptr;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.pullToRefresh);
    if (swipeRefreshLayout == null) {
      throw new NullPointerException(
          "No SwipeRefreshLayout found. Did you forget to add it to your layout with R.id.pullToRefresh?");
    }

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        onRefreshStarted();
      }
    });
  }

  @Override public void onDestroyView() {
    super.onDestroyView();

    swipeRefreshLayout = null;
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

    swipeRefreshLayout.setVisibility(View.VISIBLE);
    swipeRefreshLayout.setRefreshing(false);
  }

  /**
   * <p>
   * Called from the {@link SwipeRefreshLayout.OnRefreshListener}.<br/>
   * Default: call of {@link #loadData(boolean)}.
   * </p>
   */
  protected void onRefreshStarted() {
    loadData(true);
  }
}
