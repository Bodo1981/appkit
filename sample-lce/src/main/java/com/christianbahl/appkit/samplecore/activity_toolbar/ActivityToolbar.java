package com.christianbahl.appkit.samplecore.activity_toolbar;

import android.content.Context;
import android.content.Intent;
import com.christianbahl.appkit.lce.activity.CBActivityToolbar;

/**
 * @author Christian Bahl
 */
public class ActivityToolbar extends CBActivityToolbar {

  public static Intent getStartIntent(Context context) {
    return new Intent(context, ActivityToolbar.class);
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    getSupportActionBar().setTitle("Activity with Toolbar");
  }
}
