package com.christianbahl.appkit.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import com.christianbahl.appkit.core.adapter.CBAdapterRecyclerView;
import com.christianbahl.appkit.core.viewholder.CBButterknifeViewHolder;

/**
 * @author Christian Bahl
 */
public class ListAdapter extends CBAdapterRecyclerView<String> {

  public ListAdapter(Context context) {
    super(context);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new TextViewHolder(inflater.inflate(R.layout.list_item_text, parent, false));
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, int viewType) {
    ((TextViewHolder) viewHolder).bindView(getItem(position));
  }

  static class TextViewHolder extends CBButterknifeViewHolder {

    @InjectView(R.id.text) TextView textView;

    public TextViewHolder (View v) {
      super(v);
    }

    public void bindView(String text) {
      textView.setText(text);
    }
  }
}
