package com.christianbahl.appkit.sample.viewstate.fragment_recyclerview_ptr_list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

/**
 * @author Christian Bahl
 */
public class FragmentActivtyRecyclerViewListPtr extends CBActivityFragment {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, FragmentActivtyRecyclerViewListPtr.class);
  }

  @NonNull @Override protected Fragment createFragmentToDisplay() {
    return FragmentRecyclerViewListPtrFragment.newInstance();
  }
}
