package com.christianbahl.appkit.samplecore.fragment_recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

/**
 * @author Christian Bahl
 */
public class FragmentActivtyRecyclerView extends CBActivityFragment {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, FragmentActivtyRecyclerView.class);
  }

  @Override protected Fragment createFragmentToDisplay() {
    return FragmentRecyclerViewFragment.newInstance();
  }
}
