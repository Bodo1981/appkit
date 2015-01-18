package com.christianbahl.appkit.templates.fragment;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import com.christianbahl.appkit.mvp.presenter.CBBasePresenter;
import com.christianbahl.appkit.mvp.view.CBBaseView;
import com.christianbahl.appkit.templates.R;
import com.christianbahl.appkit.templates.adapter.CBBaseAdapterRecyclerView;

/**
 * Created by cbahl on 18.01.15.
 */
public abstract class CBBaseFragmentMvpRecyclerViewPtr<D, V extends CBBaseView<D>, P extends CBBasePresenter<V, D>, A extends CBBaseAdapterRecyclerView<D>>
    extends CBBaseFragmentMvpRecyclerView<D, V, P, A> {

  protected SwipeRefreshLayout swipeRefreshLayout;

  @Override protected int getLayoutResId() {
    return R.layout.fragment_recycler_view_ptr;
  }

  @SuppressLint("WrongViewCast") @Override protected void onViewInflated(View view) {
    super.onViewInflated(view);

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

  protected void onRefreshStarted() {
    loadData(true);
  }
}
