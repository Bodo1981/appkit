package com.christianbahl.appkit.core.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.christianbahl.appkit.core.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * An activity which uses the Model-View-Presenter architecture and adds a [Toolbar] on top.
 *
 * This activity also enables {@link ActionBar#setDisplayShowHomeEnabled(boolean)} so the
 * toolbar will show the title. If you do not want this in your activity you can override this
 * in {@link #isDisplayShowTitleEnabled()}.
 *
 * The standard layout implements all necessary views. You can override the default layout in
 * {@link #getLayoutRes}. But be careful, you have to provide the necessary views!
 *
 * @author Christian Bahl
 * @see MvpLceActivity
 */
public abstract class CBActivityMvpToolbar<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends MvpLceActivity<CV, M, V, P> {

  protected Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getLayoutRes());
  }

  @Override public void onContentChanged() {
    super.onContentChanged();

    // throws an exception if toolbar is not a toolbar
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar == null) {
      throw new NullPointerException(
          "No Toolbar found. Did you forget to add it to your layout file with the id R.id.toolbar?");
    }

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(isDisplayShowTitleEnabled());

    onMvpViewCreated();

    loadData(false);
  }

  @Override protected String getErrorMessage(Throwable throwable, boolean isContentVisible) {
    return throwable.getLocalizedMessage();
  }

  /**
   * Should the title be displayed in the toolbar.
   *
   * @return `true` if title should be displayed in toolbar otherwise `false`
   */
  protected boolean isDisplayShowTitleEnabled() {
    return true;
  }

  /**
   * Provide the layout res id for the activity.
   *
   * @return layout res id
   */
  protected Integer getLayoutRes() {
    return R.layout.cb_activity_toolbar_fragment;
  }

  /**
   * Called after mvp views and toolbar are created
   */
  protected abstract void onMvpViewCreated();
}
