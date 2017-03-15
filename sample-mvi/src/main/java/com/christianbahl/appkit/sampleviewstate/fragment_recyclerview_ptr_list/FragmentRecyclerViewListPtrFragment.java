package com.christianbahl.appkit.sampleviewstate.fragment_recyclerview_ptr_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.core.view.CBMvpView;
import com.christianbahl.appkit.sampleviewstate.fragment_recyclerview.FragmentRecyclerViewAdapter;
import com.christianbahl.appkit.sampleviewstate.fragment_recyclerview_list.FragmentRecyclerViewListPresenter;
import com.christianbahl.appkit.lce.viewstate.fragment.CBFragmentMvpListRecyclerViewPtrViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewListPtrFragment extends
    CBFragmentMvpListRecyclerViewPtrViewState<String, CBMvpView<String>, FragmentRecyclerViewListPresenter, FragmentRecyclerViewAdapter> {

  private List<String> data;

  public static FragmentRecyclerViewListPtrFragment newInstance() {
    return new FragmentRecyclerViewListPtrFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @NonNull @Override protected FragmentRecyclerViewAdapter createAdapter() {
    return new FragmentRecyclerViewAdapter(getActivity());
  }

  @NonNull @Override public FragmentRecyclerViewListPresenter createPresenter() {
    return new FragmentRecyclerViewListPresenter();
  }

  @Override public void setData(List<String> data) {
    Toast.makeText(getActivity(), "Data loaded", Toast.LENGTH_LONG).show();
    this.data = data;
    adapter.setItems(data);
    adapter.notifyDataSetChanged();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadData(pullToRefresh);
  }

  @NonNull @Override public LceViewState<List<String>, CBMvpView<String>> createViewState() {
    return new RetainingLceViewState<>();
  }

  @Override public List<String> getData() {
    return data;
  }
}
