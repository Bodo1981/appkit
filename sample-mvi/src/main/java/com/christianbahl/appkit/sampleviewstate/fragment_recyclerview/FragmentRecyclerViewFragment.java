package com.christianbahl.appkit.sampleviewstate.fragment_recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.lce.viewstate.fragment.CBFragmentMvpRecyclerViewViewState;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewFragment extends
    CBFragmentMvpRecyclerViewViewState<List<String>, MvpLceView<List<String>>, FragmentRecyclerViewPresenter, FragmentRecyclerViewAdapter> {

  private List<String> data;

  public static FragmentRecyclerViewFragment newInstance() {
    return new FragmentRecyclerViewFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @NonNull @Override protected FragmentRecyclerViewAdapter createAdapter() {
    return new FragmentRecyclerViewAdapter(getActivity());
  }

  @NonNull @Override public FragmentRecyclerViewPresenter createPresenter() {
    return new FragmentRecyclerViewPresenter();
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

  @NonNull @Override public LceViewState<List<String>, MvpLceView<List<String>>> createViewState() {
    return new RetainingLceViewState<>();
  }

  @Override public List<String> getData() {
    return data;
  }
}
