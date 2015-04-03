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
package com.christianbahl.appkit.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.christianbahl.appkit.R;
import com.christianbahl.appkit.adapter.CBBaseAdapterRecyclerView;
import com.christianbahl.appkit.presenter.CBPresenter;
import com.christianbahl.appkit.view.CBMvpView;

/**
 * A fragment which uses the Model-View-Presenter architecture.
 *
 * <p>
 * The content view is a {@link RecyclerView} with the id <code>R.layout.content_view</code>
 * </p>
 *
 * <p>
 * You have to implement the {@link A} for the {@link RecyclerView} in {@link #createAdapter()}.
 * </p>
 *
 * @author Christian Bahl
 * @see CBFragmentMvp
 */
public abstract class CBFragmentMvpRecyclerView<D, V extends CBMvpView<D>, P extends CBPresenter<V>, A extends CBBaseAdapterRecyclerView<D>>
    extends CBFragmentMvp<RecyclerView, D, V, P> {

  protected A adapter;
  protected View emptyView;

  @Override protected Integer getLayoutResId() {
    return R.layout.cb_fragment_recycler_view;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    emptyView = view.findViewById(R.id.empty_view);
    if (emptyView == null) {
      throw new IllegalStateException(
          "Empty View is null. Do you have a View with id = R.id.emptyView in your xml layout?");
    }
  }

  @Override protected void onPresenterCreated() {
    adapter = createAdapter();
    if (adapter == null) {
      throw new IllegalStateException(
          "Adapter is null. Did you forget to return an adapter in #createAdapter()?");
    }

    contentView.setAdapter(adapter);

    RecyclerView.LayoutManager layoutManager = createRecyclerViewLayoutManager();
    if (layoutManager == null) {
      throw new IllegalStateException(
          "The RecyclerView.LayoutManager is not specified. You have to provide a RecyclerView.LayoutManager by #createRecyclerViewLayoutManager()");
    } else {
      contentView.setLayoutManager(layoutManager);
    }
  }

  @Override public void showContent() {
    super.showContent();

    if (adapter.getItemCount() == 0) {
      emptyView.setVisibility(View.VISIBLE);
    } else {
      emptyView.setVisibility(View.GONE);
    }
  }

  @Override public void showLoading(boolean isContentVisible) {
    super.showLoading(isContentVisible);

    if (isContentVisible && emptyView.getVisibility() == View.VISIBLE) {
      emptyView.setVisibility(View.GONE);
    }
  }

  @Override public void showError(Exception e, boolean isContentVisible) {
    super.showError(e, isContentVisible);

    if (isContentVisible && emptyView.getVisibility() == View.VISIBLE) {
      emptyView.setVisibility(View.GONE);
    }
  }

  /**
   * Creates the {@link RecyclerView.LayoutManager}. <br />
   * Default: {@link LinearLayoutManager}
   *
   * @return {@link RecyclerView.LayoutManager}
   */
  protected RecyclerView.LayoutManager createRecyclerViewLayoutManager() {
    return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
  }

  /**
   * Creates the {@link A}. <br />
   * Called in {@link #onPresenterCreated()}
   *
   * @return {@link A}
   */
  protected abstract A createAdapter();
}
