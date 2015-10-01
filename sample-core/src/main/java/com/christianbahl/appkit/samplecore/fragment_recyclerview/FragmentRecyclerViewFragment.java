package com.christianbahl.appkit.samplecore.fragment_recyclerview;

import android.widget.Toast;
import com.christianbahl.appkit.core.common.view.CBMvpView;
import com.christianbahl.appkit.core.fragment.CBFragmentMvpRecyclerView;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewFragment extends
    CBFragmentMvpRecyclerView<String, CBMvpView<String>, FragmentRecyclerViewPresenter, FragmentRecyclerViewAdapter> {

  public static FragmentRecyclerViewFragment newInstance() {
    return new FragmentRecyclerViewFragment();
  }

  @Override protected FragmentRecyclerViewAdapter createAdapter() {
    return new FragmentRecyclerViewAdapter(getActivity());
  }

  @Override public FragmentRecyclerViewPresenter createPresenter() {
    return new FragmentRecyclerViewPresenter();
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
