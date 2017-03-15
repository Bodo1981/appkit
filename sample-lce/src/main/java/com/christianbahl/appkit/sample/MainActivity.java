package com.christianbahl.appkit.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.christianbahl.appkit.sample.lce.activity_fragment.ActivityFragment;
import com.christianbahl.appkit.sample.lce.activity_toolbar.ActivityToolbar;
import com.christianbahl.appkit.sample.lce.activity_toolbar_fragment.ActivityToolbarFragment;
import com.christianbahl.appkit.sample.lce.activity_toolbar_fragment_mvp.ActivityToolbarFragmentMvp;
import com.christianbahl.appkit.sample.lce.activity_toolbar_mvp.ActivityToolbarMvp;
import com.christianbahl.appkit.sample.lce.activity_toolbar_tabs_mvp.ActivityToolbarTabsMvp;
import com.christianbahl.appkit.sample.lce.fragment_recyclerview.FragmentActivityRecyclerView;
import com.christianbahl.appkit.sample.lce.fragment_recyclerview_list.FragmentActivityRecyclerViewList;
import com.christianbahl.appkit.sample.lce.fragment_recyclerview_list_ptr.FragmentActivityRecyclerViewListPtr;
import com.christianbahl.appkit.sample.lce.fragment_recyclerview_parallax.FragmentActivityRecyclerViewParallax;
import com.christianbahl.appkit.sample.lce.fragment_recyclerview_ptr.FragmentActivityRecyclerViewPtr;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(com.christianbahl.appkit.samplecore.R.layout.activity_main);

    // Activity with Fragment
    findViewById(com.christianbahl.appkit.samplecore.R.id.activity_fragment).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityFragment.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar
    findViewById(com.christianbahl.appkit.samplecore.R.id.activity_toolbar).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbar.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar and Fragment
    findViewById(com.christianbahl.appkit.samplecore.R.id.activity_toolbar_fragment).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbarFragment.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar (MVP)
    findViewById(com.christianbahl.appkit.samplecore.R.id.activity_mvp_toolbar).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbarMvp.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar and Fragment (MVP)
    findViewById(com.christianbahl.appkit.samplecore.R.id.activity_mvp_toolbar_fragment).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbarFragmentMvp.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar and Tabs (MVP)
    findViewById(com.christianbahl.appkit.samplecore.R.id.activity_mvp_toolbar_tabs).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbarTabsMvp.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView
    findViewById(com.christianbahl.appkit.samplecore.R.id.fragment_recyclerview).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivityRecyclerView.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView (List)
    findViewById(com.christianbahl.appkit.samplecore.R.id.fragment_recyclerview_list).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivityRecyclerViewList.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView PullToRefresh
    findViewById(com.christianbahl.appkit.samplecore.R.id.fragment_recyclerview_ptr).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivityRecyclerViewPtr.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView PullToRefresh (List)
    findViewById(com.christianbahl.appkit.samplecore.R.id.fragment_recyclerview_ptr_list).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivityRecyclerViewListPtr.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView Parallax
    findViewById(com.christianbahl.appkit.samplecore.R.id.fragment_recyclerview_parallax).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivityRecyclerViewParallax.getStartIntent(MainActivity.this));
      }
    });
  }
}
