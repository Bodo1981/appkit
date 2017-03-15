package com.christianbahl.appkit.sample.lce.fragment_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.christianbahl.appkit.adapter.CBAdapterRecyclerView;
import com.christianbahl.appkit.sample.R;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewAdapter extends CBAdapterRecyclerView<String> {

  public FragmentRecyclerViewAdapter(Context context) {
    super(context);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, int viewType) {
    ((StringViewHolder) viewHolder).bindView(items.get(position));
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new StringViewHolder(inflater.inflate(R.layout.row_text, parent, false));
  }

  class StringViewHolder extends RecyclerView.ViewHolder {

    private TextView text;

    public StringViewHolder(View v) {
      super(v);

      text = (TextView) v.findViewById(R.id.text);
    }

    public void bindView(String data) {
      text.setText(data);
    }
  }
}
