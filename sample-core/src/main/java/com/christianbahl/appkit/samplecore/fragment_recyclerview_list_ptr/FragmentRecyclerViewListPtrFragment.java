package com.christianbahl.appkit.samplecore.fragment_recyclerview_list_ptr;

import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.core.common.view.CBMvpView;
import com.christianbahl.appkit.core.fragment.CBFragmentMvpListRecyclerViewPtr;
import com.christianbahl.appkit.samplecore.fragment_recyclerview.FragmentRecyclerViewAdapter;
import com.christianbahl.appkit.samplecore.fragment_recyclerview_list.FragmentRecyclerViewListPresenter;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewListPtrFragment extends
    CBFragmentMvpListRecyclerViewPtr<String, CBMvpView<String>, FragmentRecyclerViewListPresenter, FragmentRecyclerViewAdapter> {

  public static FragmentRecyclerViewListPtrFragment newInstance() {
    return new FragmentRecyclerViewListPtrFragment();
  }

  @NonNull @Override protected FragmentRecyclerViewAdapter createAdapter() {
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
