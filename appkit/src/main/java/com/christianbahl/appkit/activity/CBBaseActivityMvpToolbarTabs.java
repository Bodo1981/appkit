package com.christianbahl.appkit.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import com.astuetz.PagerSlidingTabStrip;
import com.christianbahl.appkit.R;
import com.christianbahl.appkit.presenter.CBBasePresenter;
import com.christianbahl.appkit.view.CBBaseMvpView;

/**
 * Created by cbahl on 17.01.15.
 */
public abstract class CBBaseActivityMvpToolbarTabs<A extends PagerAdapter, D, V extends CBBaseMvpView<D>, P extends CBBasePresenter<V>>
    extends CBBaseActivityMvpToolbar<ViewPager, D, V, P> {

  protected PagerSlidingTabStrip tabs;
  protected A adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    adapter = createAdapter();

    if (adapter != null) {
      contentView.setAdapter(adapter);
      tabs.setViewPager(contentView);
    }
  }

  @Override public void onSupportContentChanged() {
    super.onSupportContentChanged();

    tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

    if (tabs == null) {
      throw new IllegalStateException("The tabs is not specified. "
          + "You have to provide a View with R.id.tabs in your inflated xml layout");
    }

    contentView.setPageMargin(20);
    contentView.setPageMarginDrawable(R.drawable.cb_viewpager_divider);
  }

  @Override protected Integer getLayoutResId() {
    return R.layout.cb_activity_toolbar_tabs;
  }

  /**
   * Creates the {@link A} for the {@link ViewPager}. <br>
   * Called in {@link #onCreate(Bundle)}
   *
   * @return {@link A}
   */
  protected abstract A createAdapter();
}
