package com.christianbahl.appkit.samplecore.fragment_recyclerview_list_ptr;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

/**
 * @author Christian Bahl
 */
public class FragmentActivityRecyclerViewListPtr extends CBActivityFragment {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, FragmentActivityRecyclerViewListPtr.class);
  }

  @Override protected Fragment createFragmentToDisplay() {
    return FragmentRecyclerViewListPtrFragment.newInstance();
  }
}
