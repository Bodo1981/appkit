package com.christianbahl.appkit.sampleviewstate.fragment_recyclerview_ptr;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

/**
 * @author Christian Bahl
 */
public class FragmentActivtyRecyclerViewPtr extends CBActivityFragment {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, FragmentActivtyRecyclerViewPtr.class);
  }

  @Override protected Fragment createFragmentToDisplay() {
    return FragmentRecyclerViewPtrFragment.newInstance();
  }
}
