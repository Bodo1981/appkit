package com.christianbahl.appkit.core.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import icepick.Icepick;

/**
 * Created by cbahl on 17.01.15.
 */
public abstract class CBBaseFragment extends Fragment {

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Icepick.restoreInstanceState(this, savedInstanceState);

    View v = inflater.inflate(getLayoutResId(), container, false);

    onViewInflated(v);

    onCreateView(v, container, savedInstanceState);

    return v;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    FragmentArgs.inject(this);
  }

  @Override public void onSaveInstanceState(Bundle out) {
    super.onSaveInstanceState(out);

    Icepick.saveInstanceState(this, out);
  }

  /**
   * Called after the view has been inflated from xml layout specified in {@link #getLayoutResId()}
   * and before {@link #onCreateView(android.view.View, android.view.ViewGroup, android.os.Bundle)}
   */
  protected void onViewInflated(View v) {
    ButterKnife.inject(this, v);
  }

  /**
   * Get the layout resource id for the layout that should be inflated.
   * This method will be called in {@link #onCreateView(android.view.LayoutInflater,
   * android.view.ViewGroup, android.os.Bundle)}
   */
  protected abstract int getLayoutResId();

  /**
   * Implement this method to setup the view. Butterknife and restoring savedInstanceState has
   * already been handled for you.
   *
   * @param view The inflated view from xml layout. You have to specify the xml layout resource in
   * {@link #getLayoutResId()}
   */
  protected abstract void onCreateView(View view, ViewGroup container, Bundle savedInstanceState);
}
