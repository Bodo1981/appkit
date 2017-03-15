package com.christianbahl.appkit.sample.viewstate.fragment_recyclerview_ptr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.lce.view.CBLceView;
import com.christianbahl.appkit.lce.viewstate.fragment.CBFragmentLceRecyclerViewPtrViewState;
import com.christianbahl.appkit.sample.common.StringListPresenter;
import com.christianbahl.appkit.sample.viewstate.fragment_recyclerview.FragmentRecyclerViewAdapter;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewPtrFragment
    extends CBFragmentLceRecyclerViewPtrViewState<List<String>, CBLceView<String>, StringListPresenter, FragmentRecyclerViewAdapter> {

  private List<String> data;

  public static FragmentRecyclerViewPtrFragment newInstance() {
    return new FragmentRecyclerViewPtrFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @NonNull @Override protected FragmentRecyclerViewAdapter createAdapter() {
    return new FragmentRecyclerViewAdapter(getActivity());
  }

  @NonNull @Override public StringListPresenter createPresenter() {
    return new StringListPresenter();
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

  @NonNull @Override public LceViewState<List<String>, CBLceView<String>> createViewState() {
    return new RetainingLceViewState<>();
  }

  @Override public List<String> getData() {
    return data;
  }
}
