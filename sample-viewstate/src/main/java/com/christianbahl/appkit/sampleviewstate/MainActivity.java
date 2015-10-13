package com.christianbahl.appkit.sampleviewstate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.christianbahl.appkit.sampleviewstate.activity_toolbar.ActivityToolbar;
import com.christianbahl.appkit.sampleviewstate.activity_toolbar_fragment.ActivityToolbarFragment;
import com.christianbahl.appkit.sampleviewstate.activity_toolbar_tabs.ActivityToolbarTabs;
import com.christianbahl.appkit.sampleviewstate.fragment_recyclerview.FragmentActivityRecyclerView;
import com.christianbahl.appkit.sampleviewstate.fragment_recyclerview_list.FragmentActivityRecyclerViewList;
import com.christianbahl.appkit.sampleviewstate.fragment_recyclerview_ptr.FragmentActivtyRecyclerViewPtr;
import com.christianbahl.appkit.sampleviewstate.fragment_recyclerview_ptr_list.FragmentActivtyRecyclerViewListPtr;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

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

    // Activity with Toolbar and Tabs
    findViewById(R.id.activity_toolbar_tabs).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(ActivityToolbarTabs.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView
    findViewById(R.id.fragment_recyclerview).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivityRecyclerView.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView (List)
    findViewById(R.id.fragment_recyclerview_list).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivityRecyclerViewList.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView PullToRefresh
    findViewById(R.id.fragment_recyclerview_ptr).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivtyRecyclerViewPtr.getStartIntent(MainActivity.this));
      }
    });

    // Fragment RecyclerView PullToRefresh (List)
    findViewById(R.id.fragment_recyclerview_ptr_list).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(FragmentActivtyRecyclerViewListPtr.getStartIntent(MainActivity.this));
      }
    });
  }
}
