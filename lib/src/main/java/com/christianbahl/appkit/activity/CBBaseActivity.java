package com.christianbahl.appkit.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * A very base empty activity.
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
 * @author Christian Bahl
 */
public abstract class CBBaseActivity extends ActionBarActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Icepick.restoreInstanceState(this, savedInstanceState);

    Integer layoutResId = getLayoutResId();
    if (layoutResId != null) {
      setContentView(layoutResId);
    }
  }

  @Override public void onSupportContentChanged() {
    super.onSupportContentChanged();

    ButterKnife.inject(this);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    Icepick.saveInstanceState(this, outState);
  }

  /**
   * Return the layout res id. <br />
   * Called in {@link #onCreate(Bundle)}
   *
   * @return layout res id
   */
  protected abstract Integer getLayoutResId();
}
