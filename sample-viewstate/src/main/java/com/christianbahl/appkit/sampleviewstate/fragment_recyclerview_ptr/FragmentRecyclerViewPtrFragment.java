package com.christianbahl.appkit.sampleviewstate.fragment_recyclerview_ptr;

import android.os.Bundle;
import android.widget.Toast;
import com.christianbahl.appkit.core.common.view.CBMvpView;
import com.christianbahl.appkit.sampleviewstate.fragment_recyclerview.FragmentRecyclerViewAdapter;
import com.christianbahl.appkit.sampleviewstate.fragment_recyclerview.FragmentRecyclerViewPresenter;
import com.christianbahl.appkit.viewstate.fragment.CBFragmentMvpRecyclerViewPtrViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewPtrFragment extends
    CBFragmentMvpRecyclerViewPtrViewState<String, CBMvpView<String>, FragmentRecyclerViewPresenter, FragmentRecyclerViewAdapter> {

  private List<String> data;

  public static FragmentRecyclerViewPtrFragment newInstance() {
    return new FragmentRecyclerViewPtrFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @Override protected FragmentRecyclerViewAdapter createAdapter() {
    return new FragmentRecyclerViewAdapter(getActivity());
  }

  @Override public FragmentRecyclerViewPresenter createPresenter() {
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

  @Override public LceViewState<List<String>, CBMvpView<String>> createViewState() {
    return new RetainingLceViewState<>();
  }

  @Override public List<String> getData() {
    return data;
  }
}
