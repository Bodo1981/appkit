package com.christianbahl.appkit.samplecore.activity_toolbar_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityToolbarFragment;
import com.christianbahl.appkit.samplecore.activity_fragment.FragmentToDisplay;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarFragment extends CBActivityToolbarFragment {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbarFragment.class);
  }

  @Override protected Fragment createFragmentToDisplay() {
    return FragmentToDisplay.newInstance();
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    getSupportActionBar().setTitle("Activity with Toolbar and Fragment");
  }
}
