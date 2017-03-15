package com.christianbahl.appkit.samplecore.fragment_recyclerview_ptr;

import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.lce.fragment.CBFragmentLceRecyclerViewPtr;
import com.christianbahl.appkit.samplecore.fragment_recyclerview.FragmentRecyclerViewAdapter;
import com.christianbahl.appkit.samplecore.fragment_recyclerview.FragmentRecyclerViewPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewPtrFragment extends
    CBFragmentLceRecyclerViewPtr<List<String>, MvpLceView<List<String>>, FragmentRecyclerViewPresenter, FragmentRecyclerViewAdapter> {

  public static FragmentRecyclerViewPtrFragment newInstance() {
    return new FragmentRecyclerViewPtrFragment();
  }

  @NonNull @Override protected FragmentRecyclerViewAdapter createAdapter() {
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
