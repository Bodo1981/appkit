package com.christianbahl.appkit.samplecore.fragment_recyclerview_list;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

/**
 * @author Christian Bahl
 */
public class FragmentActivityRecyclerViewList extends CBActivityFragment {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, FragmentActivityRecyclerViewList.class);
  }

  @Override protected Fragment createFragmentToDisplay() {
    return FragmentRecyclerViewListFragment.newInstance();
  }
}
