package com.christianbahl.appkit.samplecore.activity_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

/**
 * @author Christian Bahl
 */
public class ActivityFragment extends CBActivityFragment {

  @NonNull @Override protected Fragment createFragmentToDisplay() {
    setTitle("Activity with Fragment");

    return FragmentToDisplay.newInstance();
  }

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityFragment.class);
  }
}
