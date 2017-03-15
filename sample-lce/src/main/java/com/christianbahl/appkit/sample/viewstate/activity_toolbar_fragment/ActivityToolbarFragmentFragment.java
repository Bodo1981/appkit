package com.christianbahl.appkit.sample.viewstate.activity_toolbar_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.christianbahl.appkit.sampleviewstate.R;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarFragmentFragment extends Fragment {

  public static ActivityToolbarFragmentFragment newInstance() {
    return new ActivityToolbarFragmentFragment();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_to_display, container, false);

    ((TextView)v.findViewById(R.id.textView)).setText("Fragment");

    return v;
  }
}
