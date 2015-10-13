package com.christianbahl.appkit.samplecore.fragment_recyclerview_ptr;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

/**
 * @author Christian Bahl
 */
public class FragmentActivityRecyclerViewPtr extends CBActivityFragment {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, FragmentActivityRecyclerViewPtr.class);
  }

  @Override protected Fragment createFragmentToDisplay() {
    return FragmentRecyclerViewPtrFragment.newInstance();
  }
}
