package com.christianbahl.appkit.samplecore.fragment_recyclerview_parallax;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

/**
 * @author Christian Bahl
 */
public class FragmentActivtyRecyclerViewParallax extends CBActivityFragment {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, FragmentActivtyRecyclerViewParallax.class);
  }

  @Override protected Fragment createFragmentToDisplay() {
    return FragmentRecyclerViewParallaxFragment.newInstance();
  }
}
