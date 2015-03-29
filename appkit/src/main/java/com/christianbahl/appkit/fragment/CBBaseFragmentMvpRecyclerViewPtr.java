package com.christianbahl.appkit.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import com.christianbahl.appkit.R;
import com.christianbahl.appkit.adapter.CBBaseAdapterRecyclerView;
import com.christianbahl.appkit.presenter.CBBasePresenter;
import com.christianbahl.appkit.view.CBBaseMvpView;

/**
 * Created by cbahl on 18.01.15.
 */
public abstract class CBBaseFragmentMvpRecyclerViewPtr<D, V extends CBBaseMvpView<D>, P extends CBBasePresenter<V>, A extends CBBaseAdapterRecyclerView<D>>
    extends CBBaseFragmentMvpRecyclerView<D, V, P, A> {

  protected SwipeRefreshLayout swipeRefreshLayout;

  @Override protected Integer getLayoutResId() {
    return R.layout.cb_fragment_recycler_view_ptr;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.pull_to_refresh);
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
  }

  @Override public void showLoading(boolean isContentVisible) {
    super.showLoading(isContentVisible);

    if (!isContentVisible) {
      swipeRefreshLayout.setVisibility(View.GONE);
    }
  }

  @Override public void showError(Exception e, boolean isContentVisible) {
    super.showError(e, isContentVisible);

    swipeRefreshLayout.setVisibility(View.GONE);
  }

  /**
   * Called from the {@link SwipeRefreshLayout.OnRefreshListener}.<br>
   * Default: call of {@link #loadData(boolean)}
   */
  protected void onRefreshStarted() {
    loadData(true);
  }
}
