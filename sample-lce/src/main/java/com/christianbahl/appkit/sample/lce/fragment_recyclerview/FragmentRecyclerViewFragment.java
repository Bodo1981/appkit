package com.christianbahl.appkit.sample.lce.fragment_recyclerview;

import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.lce.fragment.CBFragmentLceRecyclerView;
import com.christianbahl.appkit.lce.view.CBLceView;
import com.christianbahl.appkit.sample.common.StringListPresenter;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewFragment
    extends CBFragmentLceRecyclerView<List<String>, CBLceView<String>, StringListPresenter, FragmentRecyclerViewAdapter> {

  public static FragmentRecyclerViewFragment newInstance() {
    return new FragmentRecyclerViewFragment();
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
