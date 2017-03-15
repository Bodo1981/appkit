package com.christianbahl.appkit.sample.lce.fragment_recyclerview_list;

import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.lce.fragment.CBFragmentLceListRecyclerView;
import com.christianbahl.appkit.lce.view.CBLceView;
import com.christianbahl.appkit.sample.common.StringListPresenter;
import com.christianbahl.appkit.sample.lce.fragment_recyclerview.FragmentRecyclerViewAdapter;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewListFragment
    extends CBFragmentLceListRecyclerView<String, CBLceView<String>, StringListPresenter, FragmentRecyclerViewAdapter> {

  public static FragmentRecyclerViewListFragment newInstance() {
    return new FragmentRecyclerViewListFragment();
  }

  @NonNull @Override protected FragmentRecyclerViewAdapter createAdapter() {
    return new FragmentRecyclerViewAdapter(getActivity());
  }

  @NonNull @Override public StringListPresenter createPresenter() {
    return new StringListPresenter();
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
