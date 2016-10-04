package com.christianbahl.appkit.viewstate.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import com.christianbahl.appkit.viewstate.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateActivity;

/**
 * @author Christian Bahl
 */
public abstract class CBActivityToolbarViewState<V extends MvpView, P extends MvpPresenter<V>> extends MvpViewStateActivity<V, P> {

  public Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getIntent() != null && getIntent().getExtras() != null) {
      readExtras(getIntent().getExtras());
    }

    Integer layoutRes = getLayoutRes();
    if (layoutRes == null) {
      throw new NullPointerException("LayoutRes is null. Did you return null in getLayoutRes()?");
    }
    setContentView(layoutRes);
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    toolbar = (Toolbar) findViewById(R.id.toolbar);

    if (toolbar == null) {
      throw new IllegalStateException("The toolbar is null. "
          + "You have to provide a View with R.id.toolbar in your inflated xml layout");
    }

    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayShowTitleEnabled(isDisplayShowTitleEnabled());
    }
  }

  /**
   * <p>
   * Should the title be displayed in the toolbar.
   * </p>
   *
   * @return <code>true</code> if title should be displayed in toolbar otherwise <code>false</code>
   */
  protected boolean isDisplayShowTitleEnabled() {
    return true;
  }

  /**
   * <p>
   * Provide the layout res id for the activity.
   * </p>
   *
   * @return layout res id
   */
  @NonNull protected Integer getLayoutRes() {
    return R.layout.cb_activity_toolbar;
  }

  /**
   * <p>
   * Handle extra bundle data.
   * </p>
   *
   * @param bundle bundle with extras passed to activity
   */
  protected void readExtras(@NonNull Bundle bundle) {

  }
}
