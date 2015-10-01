package com.christianbahl.appkit.samplecore.activity_toolbar_tabs_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.christianbahl.appkit.samplecore.R;

/**
 * @author Christian Bahl
 */
public class ActivityToolbarTabsMvpFragment extends Fragment {

  private String title;

  public static ActivityToolbarTabsMvpFragment newInstance(String title) {
    ActivityToolbarTabsMvpFragment fragment = new ActivityToolbarTabsMvpFragment();
    fragment.setTitle(title);

    return fragment;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_to_display, container, false);

    ((TextView)v.findViewById(R.id.textView)).setText(title);

    return v;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
