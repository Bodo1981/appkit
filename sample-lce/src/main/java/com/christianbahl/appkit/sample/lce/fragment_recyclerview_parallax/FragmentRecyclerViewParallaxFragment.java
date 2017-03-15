package com.christianbahl.appkit.sample.lce.fragment_recyclerview_parallax;

import android.support.annotation.NonNull;
import android.widget.Toast;
import com.christianbahl.appkit.lce.fragment.CBFragmentLceRecyclerViewPtr;
import com.christianbahl.appkit.lce.view.CBLceView;
import com.christianbahl.appkit.sample.common.StringListPresenter;
import java.util.List;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewParallaxFragment
    extends CBFragmentLceRecyclerViewPtr<List<String>, CBLceView<String>, StringListPresenter, FragmentRecyclerViewParallaxAdapter> {

  public static FragmentRecyclerViewParallaxFragment newInstance() {
    return new FragmentRecyclerViewParallaxFragment();
  }

  @NonNull @Override protected FragmentRecyclerViewParallaxAdapter createAdapter() {
    return new FragmentRecyclerViewParallaxAdapter(getActivity(), contentView);
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
