package com.christianbahl.appkit.core.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.christianbahl.appkit.core.R;
import com.christianbahl.appkit.core.adapter.CBAdapterRecyclerView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import java.util.List;

/**
 * A fragment which uses the Model-View-Presenter architecture.
 *
 * The content view is a {@link RecyclerView} with the id `R.layout.contentView`
 *
 * You have to implement the {@link A} for the {@link RecyclerView} in {@link #createAdapter()}.
 *
 * @author Christian Bahl
 * @see MvpLceFragment
 */
public abstract class CBFragmentMvpRecyclerView<AD, M, V extends MvpLceView<M>, P extends MvpPresenter<V>, A extends CBAdapterRecyclerView<AD, List<AD>>>
    extends MvpLceFragment<RecyclerView, M, V, P> {

  protected A adapter;
  protected View emptyView;

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

  @Override protected int getLayoutRes() {
    return R.layout.cb_fragment_recycler_view;
  }

  @Override protected String getErrorMessage(Throwable throwable, boolean isContentVisible) {
    return throwable.getLocalizedMessage();
  }

  /**
   * Creates the {@link RecyclerView.LayoutManager}.
   * Default: {@link LinearLayoutManager}
   *
   * @return {@link RecyclerView.LayoutManager}
   */
  protected RecyclerView.LayoutManager createRecyclerViewLayoutManager() {
    return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
  }

  /**
   * Creates the {@link A}.
   * Called in {@link #onViewCreated}
   *
   * @return {@link A}
   */
  protected abstract A createAdapter();

  /**
   * Called after the mvp views and the recycler view are created
   *
   * @param view {@link View}
   * @param savedInstanceState {@link Bundle}
   */
  protected abstract void onMvpViewCreated(View view, Bundle savedInstanceState);
}
