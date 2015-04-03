/*
 * Copyright 2015 Christian Bahl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.christianbahl.appkit.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import icepick.Icepick;

/**
 * A very base {@link Fragment}.
 *
 * <p>
 * Uses <b>FragmentArgs</b>: Annotation processing to create a builder for creating the
 * {@link Fragment} with different values.
 * </p>
 *
 * <p>
 * Uses <b>Icepick</b>: Annotation processing to make working with onSaveInstanceState()
 * much easier. Simply use <code>@Icicle</code> to mark fields which state should be saved in
 * onSaveInstanceState().
 * </p>
 *
 * <p>
 * Uses <b>Butterknife</b>: Annotation processing to inject views. Simple use
 * <code>@InjectView</code> to mark views which should be initialised.
 * </p>
 *
 * <p>
 * If you want to use dependency injection libraries like dagger you can override {@link
 * #injectDependencies()} and implement dependency injection right there
 * </p>
 *
 * @author Christian Bahl
 * @see Fragment
 */
public abstract class CBFragment extends Fragment {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    injectDependencies();

    FragmentArgs.inject(this);
    Icepick.restoreInstanceState(this, savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Integer layoutResId = getLayoutResId();
    if (layoutResId == null) {
      throw new IllegalArgumentException(
          "LayoutResId is null. Did you forget to return a layout res id in getLayoutResId()");
    }

    return inflater.inflate(layoutResId, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ButterKnife.inject(this, view);
  }

  @Override public void onSaveInstanceState(Bundle out) {
    super.onSaveInstanceState(out);

    Icepick.saveInstanceState(this, out);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();

    ButterKnife.reset(this);
  }

  /**
   * In this method you can inject dependencies (e.g. by using dagger).<br />
   * Called in {@link #onCreate(Bundle)}
   */
  protected void injectDependencies() {

  }

  /**
   * Get the layout resource id for the layout that should be inflated.
   * This method will be called in {@link #onCreateView(android.view.LayoutInflater,
   * android.view.ViewGroup, android.os.Bundle)}
   */
  protected abstract Integer getLayoutResId();
}
