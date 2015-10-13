package com.christianbahl.appkit.samplecore.fragment_recyclerview_list;

import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.core.common.view.CBMvpView;
import com.christianbahl.appkit.core.fragment.CBFragmentMvpListRecyclerView;
import com.christianbahl.appkit.samplecore.fragment_recyclerview.FragmentRecyclerViewAdapter;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewListFragment extends
    CBFragmentMvpListRecyclerView<String, CBMvpView<String>, FragmentRecyclerViewListPresenter, FragmentRecyclerViewAdapter> {

  public static FragmentRecyclerViewListFragment newInstance() {
    return new FragmentRecyclerViewListFragment();
  }

  @Override protected FragmentRecyclerViewAdapter createAdapter() {
    return new FragmentRecyclerViewAdapter(getActivity());
  }

  @NonNull @Override public FragmentRecyclerViewListPresenter createPresenter() {
    return new FragmentRecyclerViewListPresenter();
  }

  @Override public void setData(List<String> data) {
    Toast.makeText(getActivity(), "Data loaded", Toast.LENGTH_LONG).show();
    adapter.setItems(data);
    adapter.notifyDataSetChanged();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }
}
