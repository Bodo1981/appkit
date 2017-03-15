package com.christianbahl.appkit.sample.lce.activity_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.christianbahl.appkit.sample.R;

/**
 * @author Christian Bahl
 */
public class FragmentToDisplay extends Fragment {

  public static FragmentToDisplay newInstance() {
    return new FragmentToDisplay();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_to_display, container, false);

    ((TextView) v.findViewById(R.id.textView)).setText("Fragment");

    return v;
  }
}
