package com.christianbahl.appkit.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;

/**
 * A base [RecyclerView.Adapter] with a [Context], [LayoutInflater] and
 * a [List] of [D] which you would like to display.
 *
 * This activity has an additional implementation of [.onBindViewHolder] which has the view type as
 * parameter.
 *
 * @author Christian Bahl
 * @see RecyclerView.Adapter
 */
public abstract class CBAdapterRecyclerView<M, L extends List<M>>
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  protected LayoutInflater inflater;
  protected Context context;
  protected L items;

  public CBAdapterRecyclerView(Context context) {
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  /**
   * Get item at position
   *
   * @param position position
   * @return item at position
   */
  protected M getItem(int position) {
    return items == null ? null : items.get(position);
  }

  /**
   * Add items
   *
   * If {@link #items} is null an empty {@link L} will be created first.
   *
   * @param newItems {@link L}
   */
  public void addNewItems(L newItems) {
    if (this.items == null) {
      this.items = createEmptyList();
    }

    this.items.addAll(newItems);
  }

  /**
   * Add a single item.
   *
   * If {@link #items} is null an empty {@link L} will be created first.
   *
   * @param newItem {@link M}
   */
  public void addNewItem(M newItem) {
    if (this.items == null) {
      this.items = createEmptyList();
    }

    this.items.add(newItem);
  }

  /**
   * Creates an empty [L]
   *
   * @return empty [L]
   */
  @SuppressWarnings("unchecked") private L createEmptyList() {
    return (L) new ArrayList<M>();
  }

  @Override public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    onBindViewHolder(viewHolder, position, getItemViewType(position));
  }

  /**
   * Bind the [RecyclerView.ViewHolder]
   *
   * @param viewHolder [RecyclerView.ViewHolder]
   * @param position position
   * @param viewType view type
   */
  public abstract void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position,
      int viewType);
}
