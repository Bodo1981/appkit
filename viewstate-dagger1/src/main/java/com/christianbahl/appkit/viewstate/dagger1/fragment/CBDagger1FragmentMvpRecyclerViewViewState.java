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
package com.christianbahl.appkit.viewstate.dagger1.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.christianbahl.appkit.core.adapter.CBAdapterRecyclerView;
import com.christianbahl.appkit.viewstate.dagger1.R;
import com.hannesdorfmann.mosby.dagger1.viewstate.lce.Dagger1MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import java.util.List;

/**
 * <p>
 * A fragment which uses the Model-View-Presenter architecture with {@link ViewState} support and
 * Dagger 1 for dependency injection.
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
 * @see Dagger1MvpLceViewStateFragment
 */
public abstract class CBDagger1FragmentMvpRecyclerViewViewState<M, V extends com.hannesdorfmann.mosby.mvp.lce.MvpLceView<List<M>>, P extends MvpPresenter<V>, A extends CBAdapterRecyclerView<M>>
    extends Dagger1MvpLceViewStateFragment<RecyclerView, List<M>, V, P> {

  protected A adapter;
  protected View emptyView;

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    adapter = createAdapter();
    if (adapter == null) {
      throw new IllegalStateException(
          "Adapter is null. Did you forget to return an adapter in #createAdapter()?");
    }

    contentView.setAdapter(adapter);

    emptyView = view.findViewById(R.id.emptyView);
    if (emptyView == null) {
      throw new IllegalStateException(
          "Empty View is null. Do you have a View with id = R.id.emptyView in your xml layout?");
    }

    RecyclerView.LayoutManager layoutManager = createRecyclerViewLayoutManager();
    if (layoutManager == null) {
      throw new IllegalStateException(
          "The RecyclerView.LayoutManager is not specified. You have to provide a "
              + "RecyclerView.LayoutManager by #createRecyclerViewLayoutManager()");
    } else {
      contentView.setLayoutManager(layoutManager);
    }

    onMvpViewCreated(view, savedInstanceState);

    loadData(false);
  }

  @Override protected int getLayoutRes() {
    return R.layout.cb_fragment_recycler_view;
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
   * Creates the {@link RecyclerView.LayoutManager}. <br />
   * Default: {@link LinearLayoutManager}
   *
   * @return {@link RecyclerView.LayoutManager}
   */
  protected RecyclerView.LayoutManager createRecyclerViewLayoutManager() {
    return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
  }

  /**
   * Called after the mvp views and the recycler view are created
   *
   * @param view {@link View}
   * @param savedInstanceState {@link Bundle}
   */
  protected void onMvpViewCreated(View view, Bundle savedInstanceState) {

  }

  /**
   * Creates the {@link A}. <br />
   * Called in {@link #onViewCreated(View, Bundle)}
   *
   * @return {@link A}
   */
  protected abstract A createAdapter();
}
