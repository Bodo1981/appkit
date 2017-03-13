package com.christianbahl.appkit.samplecore.fragment_recyclerview_parallax;

import android.widget.Toast;
import com.christianbahl.appkit.core.fragment.CBFragmentMvpRecyclerViewPtr;
import com.christianbahl.appkit.samplecore.fragment_recyclerview.FragmentRecyclerViewPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewParallaxFragment extends
    CBFragmentMvpRecyclerViewPtr<List<String>, MvpLceView<List<String>>, FragmentRecyclerViewPresenter, FragmentRecyclerViewParallaxAdapter> {

  public static FragmentRecyclerViewParallaxFragment newInstance() {
    return new FragmentRecyclerViewParallaxFragment();
  }

  @Override protected FragmentRecyclerViewParallaxAdapter createAdapter() {
    return new FragmentRecyclerViewParallaxAdapter(getActivity(), contentView);
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
