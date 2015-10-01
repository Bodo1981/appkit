package com.christianbahl.appkit.samplecore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.christianbahl.appkit.samplecore.activity_fragment.ActivityFragment;
import com.christianbahl.appkit.samplecore.activity_toolbar.ActivityToolbar;
import com.christianbahl.appkit.samplecore.activity_toolbar_fragment.ActivityToolbarFragment;
import com.christianbahl.appkit.samplecore.activity_toolbar_fragment_mvp.ActivityToolbarFragmentMvp;
import com.christianbahl.appkit.samplecore.activity_toolbar_mvp.ActivityToolbarMvp;
import com.christianbahl.appkit.samplecore.activity_toolbar_tabs_mvp.ActivityToolbarTabsMvp;
import com.christianbahl.appkit.samplecore.fragment_recyclerview.FragmentActivtyRecyclerView;
import com.christianbahl.appkit.samplecore.fragment_recyclerview_parallax.FragmentActivtyRecyclerViewParallax;
import com.christianbahl.appkit.samplecore.fragment_recyclerview_ptr.FragmentActivtyRecyclerViewPtr;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Activity with Fragment
    findViewById(R.id.activity_fragment).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityFragment.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar
    findViewById(R.id.activity_toolbar).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbar.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar and Fragment
    findViewById(R.id.activity_toolbar_fragment).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbarFragment.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar (MVP)
    findViewById(R.id.activity_mvp_toolbar).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbarMvp.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar and Fragment (MVP)
    findViewById(R.id.activity_mvp_toolbar_fragment).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbarFragmentMvp.getStartIntent(MainActivity.this));
      }
    });

    // Activity with Toolbar and Tabs (MVP)
    findViewById(R.id.activity_mvp_toolbar_tabs).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbarTabsMvp.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView
    findViewById(R.id.fragment_recyclerview).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivtyRecyclerView.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView PullToRefresh
    findViewById(R.id.fragment_recyclerview_ptr).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivtyRecyclerViewPtr.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView Parallax
    findViewById(R.id.fragment_recyclerview_parallax).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivtyRecyclerViewParallax.getStartIntent(MainActivity.this));
      }
    });
  }
}
