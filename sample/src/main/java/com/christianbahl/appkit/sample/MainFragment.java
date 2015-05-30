package com.christianbahl.appkit.sample;

import com.christianbahl.appkit.core.fragment.CBFragmentMvpRecyclerView;
import com.christianbahl.appkit.core.view.CBMvpView;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class MainFragment
    extends CBFragmentMvpRecyclerView<String, CBMvpView<String>, ListPresenter, ListAdapter>
    implements CBMvpView<String> {

  @Override protected ListAdapter createAdapter() {
    return new ListAdapter(getActivity());
  }

  @Override public ListPresenter createPresenter() {
    return new ListPresenter();
  }

  @Override public void setData(List<String> data) {
    adapter.setItems(data);
    adapter.notifyDataSetChanged();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadList(pullToRefresh);
  }
}
