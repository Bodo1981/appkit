package com.christianbahl.appkit.sample;

import android.support.v4.app.Fragment;
import com.christianbahl.appkit.core.activity.CBActivityFragment;

public class MainActivity extends CBActivityFragment  {

  @Override protected Fragment createFragmentToDisplay() {
    return new MainFragment();
  }
}
