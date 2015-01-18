package com.christianbahl.appkit.templates.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.christianbahl.appkit.mvp.presenter.CBBasePresenter;
import com.christianbahl.appkit.mvp.view.CBBaseView;
import com.christianbahl.appkit.templates.R;
import com.christianbahl.appkit.templates.adapter.CBBaseAdapterRecyclerView;

/**
 * Created by cbahl on 18.01.15.
 */
public abstract class CBBaseFragmentMvpRecyclerView<D, V extends CBBaseView<D>, P extends CBBasePresenter<V, D>, A extends CBBaseAdapterRecyclerView<D>>
    extends CBBaseFragmentMvp<RecyclerView, D, V, P> {

  protected A adapter;
  protected View emptyView;

  @Override protected int getLayoutResId() {
    return R.layout.fragment_recycler_view;
  }

  @Override protected void onPresenterCreated(View view, ViewGroup container,
      Bundle savedInstanceState) {
    adapter = createAdapter();

    onAdapterCreated(view, container, savedInstanceState);
  }

  @Override protected void onCreateView(View view, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(view, container, savedInstanceState);

    RecyclerView.LayoutManager layoutManager = createRecyclerViewLayoutManager();
    if (layoutManager == null) {
      throw new IllegalStateException(
          "The RecyclerView.LayoutManager is not specified. You have to provide a RecyclerView.LayoutManager by #createRecyclerViewLayoutManager()");
    } else {
      contentView.setLayoutManager(layoutManager);
    }
  }

  @Override protected void onViewInflated(View view) {
    super.onViewInflated(view);

    emptyView = view.findViewById(R.id.empty_view);

    if (emptyView == null) {
      throw new IllegalStateException(
          "Empty View is null. Do you have a View with id = R.id.emptyView in your xml layout?");
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

  protected void onAdapterCreated(View view, ViewGroup container, Bundle savedInstanceState) {
    if (adapter != null) {
      contentView.setAdapter(adapter);
    }

    init(view, container, savedInstanceState);
  }

  protected RecyclerView.LayoutManager createRecyclerViewLayoutManager() {
    return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
  }

  /**
   * Creates the {@link A}. Called in {@link #onPresenterCreated(View, ViewGroup, Bundle)}
   *
   * @return {@link A}
   */
  protected abstract A createAdapter();

  /**
   * Implement this method to setup the view. Butterknife and restoring savedInstanceState has
   * already been handled for you. Also the layout has already been inflated ({@link
   * #getLayoutResId()}). {@link P} and {@link A} are also initialized.
   * Directly called before {@link #loadData(boolean)}
   *
   * @param view The inflated view from xml layout. You have to specify the xml layout resource in
   * {@link #getLayoutResId()}
   * @param container The container
   * @param savedInstanceState The saved instance state
   */
  protected abstract void init(View view, ViewGroup container, Bundle savedInstanceState);
}
