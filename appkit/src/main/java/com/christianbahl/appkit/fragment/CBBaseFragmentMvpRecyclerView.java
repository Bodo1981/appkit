package com.christianbahl.appkit.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.christianbahl.appkit.R;
import com.christianbahl.appkit.adapter.CBBaseAdapterRecyclerView;
import com.christianbahl.appkit.presenter.CBBasePresenter;
import com.christianbahl.appkit.view.CBBaseMvpView;

/**
 * Created by cbahl on 18.01.15.
 */
public abstract class CBBaseFragmentMvpRecyclerView<D, V extends CBBaseMvpView<D>, P extends CBBasePresenter<V>, A extends CBBaseAdapterRecyclerView<D>>
    extends CBBaseFragmentMvp<RecyclerView, D, V, P> {

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

  protected RecyclerView.LayoutManager createRecyclerViewLayoutManager() {
    return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
  }

  /**
   * Creates the {@link A}. <br>
   * Called in {@link #onViewCreated(View, Bundle)}
   *
   * @return {@link A}
   */
  protected abstract A createAdapter();
}
