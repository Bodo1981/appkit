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
package com.christianbahl.appkit.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.christianbahl.appkit.core.R;
import com.christianbahl.appkit.core.adapter.CBAdapterRecyclerView;
import com.christianbahl.appkit.core.common.view.CBMvpView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import java.util.List;

/**
 * <p>
 * A fragment which uses the Model-View-Presenter architecture.
 * </p>
 *
 * <p>
 * The content view is a {@link RecyclerView} with the id <code>R.layout.contentView</code>
 * </p>
 *
 * <p>
 * You have to implement the {@link A} for the {@link RecyclerView} in {@link #createAdapter()}.
 * </p>
 *
 * @author Christian Bahl
 * @see MvpLceFragment
 */
public abstract class CBFragmentMvpRecyclerView<M, V extends CBMvpView<M>, P extends MvpPresenter<V>, A extends CBAdapterRecyclerView<M>>
    extends MvpLceFragment<RecyclerView, List<M>, V, P> implements CBMvpView<M> {

  protected A adapter;
  protected View emptyView;

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Integer layoutRes = getLayoutRes();
    if (layoutRes == null) {
      throw new NullPointerException("LayoutRes is null. Did you return null in getLayoutRes()?");
    }

    return inflater.inflate(layoutRes, container, false);
  }

  /**
   * <p>
   * Provide the layout res id for the activity.
   * </p>
   *
   * @return layout res id
   */
  protected Integer getLayoutRes() {
    return R.layout.cb_fragment_recycler_view;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    adapter = createAdapter();
    if (adapter == null) {
      throw new NullPointerException(
          "No Adapter found. Did you forget to create it in createAdapter()?");
    }
    contentView.setAdapter(adapter);

    emptyView = view.findViewById(R.id.emptyView);
    if (emptyView == null) {
      throw new NullPointerException(
          "No emptyView found. Did you forget to add it to your layout with R.id.emptyView?");
    }

    contentView.setLayoutManager(createRecyclerViewLayoutManager());

    onMvpViewCreated(view, savedInstanceState);

    loadData(false);
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

  @Override public void showError(Throwable e, boolean isContentVisible) {
    super.showError(e, isContentVisible);

    if (isContentVisible && emptyView.getVisibility() == View.VISIBLE) {
      emptyView.setVisibility(View.GONE);
    }
  }

  @Override protected String getErrorMessage(Throwable throwable, boolean isContentVisible) {
    return throwable.getLocalizedMessage();
  }

  /**
   * <p>
   * Creates the {@link RecyclerView.LayoutManager}.<br/>
   * Default: {@link LinearLayoutManager}.
   * </p>
   *
   * @return {@link RecyclerView.LayoutManager}
   */
  protected RecyclerView.LayoutManager createRecyclerViewLayoutManager() {
    return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
  }

  /**
   * <p>
   * Called after the mvp views and the recycler view are created.
   * </p>
   *
   * @param view view
   * @param savedInstanceState saved instance state
   */
  protected void onMvpViewCreated(View view, Bundle savedInstanceState) {

  }

  /**
   * <p>
   * Creates the {@link A}.<br/>
   * Called in {@link #onViewCreated}.
   * </p>
   *
   * @return {@link A}
   */
  protected abstract A createAdapter();
}
