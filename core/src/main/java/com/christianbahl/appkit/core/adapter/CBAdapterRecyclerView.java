/*
 * Copyright 2015 Christian Bahl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.christianbahl.appkit.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A base {@link RecyclerView.Adapter}with a {@link Context}, {@link LayoutInflater} and
 * a {@link List} of {@link M} which you would like to display.
 * </p>
 *
 * <p>
 * This activity has an additional implementation of {@link #onBindViewHolder(RecyclerView.ViewHolder,
 * int, int)} which has the view type as parameter.
 * </p>
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
    this.context = context;
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

  @Override public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }
}
