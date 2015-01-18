package com.christianbahl.appkit.templates.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbahl on 18.01.15.
 */
public abstract class CBBaseAdapterRecyclerView<D> extends RecyclerView.Adapter {

  protected LayoutInflater inflater;
  protected Context context;
  protected List<D> items;

  public CBBaseAdapterRecyclerView(Context context) {
    this.context = context;
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public void setLayoutInflater(LayoutInflater inflater) {
    this.inflater = inflater;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public List<D> getItems() {
    return items;
  }

  public void setItems(List<D> items) {
    this.items = items;
  }

  public void addNewItems(List<D> items) {
    if (this.items == null) {
      this.items = createEmptyList();
    }

    this.items.addAll(0, items);
  }

  public void addNewItem(D item) {
    if (this.items == null) {
      this.items = createEmptyList();
    }

    this.items.add(item);
  }

  private List<D> createEmptyList() {
    return new ArrayList<>();
  }

  public D getItem(int position) {
    return items.get(position);
  }

  @Override public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    onBindViewHolder(viewHolder, position, getItemViewType(position));
  }

  /**
   * Bind the {@link RecyclerView.ViewHolder}
   *
   * @param viewHolder {@link RecyclerView.ViewHolder}
   * @param position position
   * @param viewType view type
   */
  public abstract void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position,
      int viewType);
}
