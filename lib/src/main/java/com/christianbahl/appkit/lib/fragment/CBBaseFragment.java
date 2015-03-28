package com.christianbahl.appkit.lib.fragment;

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

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    FragmentArgs.inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Icepick.restoreInstanceState(this, savedInstanceState);

    Integer layoutResId = getLayoutResId();
    if (layoutResId != null) {
      return inflater.inflate(getLayoutResId(), container, false);
    }

    return null;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ButterKnife.inject(this, view);
  }

  @Override public void onSaveInstanceState(Bundle out) {
    super.onSaveInstanceState(out);

    Icepick.saveInstanceState(this, out);
  }

  /**
   * Get the layout resource id for the layout that should be inflated.
   * This method will be called in {@link #onCreateView(android.view.LayoutInflater,
   * android.view.ViewGroup, android.os.Bundle)}
   */
  protected abstract Integer getLayoutResId();
}
