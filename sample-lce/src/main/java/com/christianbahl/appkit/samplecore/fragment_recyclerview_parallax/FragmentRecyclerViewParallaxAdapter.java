package com.christianbahl.appkit.samplecore.fragment_recyclerview_parallax;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.christianbahl.appkit.adapter.CBAdapterRecyclerViewParallax;
import com.christianbahl.appkit.samplecore.R;

/**
 * @author Christian Bahl
 */
public class FragmentRecyclerViewParallaxAdapter extends CBAdapterRecyclerViewParallax<String> {

  public FragmentRecyclerViewParallaxAdapter(Context context, RecyclerView recyclerView) {
    super(context, recyclerView);
  }

  @Override public boolean isItemParallaxScrollable(int position, int itemViewType) {
    return true;
  }

  @Override
  public void doParallaxScrolling(RecyclerView.ViewHolder viewHolder, int position, int viewType,
      int pixelAlreadyScrolledOut) {
    StringViewHolder vh = (StringViewHolder) viewHolder;
    vh.text.setTranslationY(pixelAlreadyScrolledOut * 0.5f);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, int viewType) {
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
