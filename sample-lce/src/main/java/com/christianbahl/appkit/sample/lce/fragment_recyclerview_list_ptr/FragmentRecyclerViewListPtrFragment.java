package com.christianbahl.appkit.sample.lce.fragment_recyclerview_list_ptr;

import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.lce.fragment.CBFragmentLceListRecyclerViewPtr;
import com.christianbahl.appkit.lce.view.CBMvpView;
import com.christianbahl.appkit.sample.lce.common.StringListPresenter;
import com.christianbahl.appkit.sample.lce.fragment_recyclerview.FragmentRecyclerViewAdapter;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewListPtrFragment
    extends CBFragmentLceListRecyclerViewPtr<String, CBMvpView<String>, StringListPresenter, FragmentRecyclerViewAdapter> {

  public static FragmentRecyclerViewListPtrFragment newInstance() {
    return new FragmentRecyclerViewListPtrFragment();
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
