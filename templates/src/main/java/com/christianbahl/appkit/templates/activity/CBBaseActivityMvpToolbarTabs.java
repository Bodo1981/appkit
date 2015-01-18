package com.christianbahl.appkit.templates.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.astuetz.PagerSlidingTabStrip;
import com.christianbahl.appkit.mvp.presenter.CBBasePresenter;
import com.christianbahl.appkit.mvp.view.CBBaseView;
import com.christianbahl.appkit.templates.R;

/**
 * Created by cbahl on 17.01.15.
 */
public abstract class CBBaseActivityMvpToolbarTabs<A extends FragmentStatePagerAdapter, D, V extends CBBaseView<D>, P extends CBBasePresenter<V, D>>
    extends CBBaseActivityMvpToolbar<ViewPager, D, V, P> {

  protected PagerSlidingTabStrip tabs;
  protected A adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    contentView.setPageMargin(20);
    contentView.setPageMarginDrawable(R.drawable.viewpager_divider);
  }

  @Override protected void onPresenterCreated() {
    adapter = createAdapter();

    if (adapter != null) {
      contentView.setAdapter(adapter);
      tabs.setViewPager(contentView);
    }
  }

  @Override protected void injectViews() {
    super.injectViews();

    tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

    if (tabs == null) {
      throw new IllegalStateException("The tabs is not specified. "
          + "You have to provide a View with R.id.tabs in your inflated xml layout");
    }
  }

  @Override protected Integer getLayoutResId() {
    return R.layout.activity_toolbar_tabs;
  }

  /**
   * Creates the {@link A} for the {@link ViewPager}. Called in {@link #onPresenterCreated()}
   *
   * @return {@link A}
   */
  protected abstract A createAdapter();
}
