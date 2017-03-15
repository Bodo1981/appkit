package com.christianbahl.appkit.sample.lce.fragment_recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

/**
 * @author Christian Bahl
 */
public class FragmentActivityRecyclerView extends CBActivityFragment {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, FragmentActivityRecyclerView.class);
  }

  @NonNull @Override protected Fragment createFragmentToDisplay() {
    return FragmentRecyclerViewFragment.newInstance();
  }
}
