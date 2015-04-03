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
package com.christianbahl.appkit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;

/**
 * A base {@link RecyclerView.Adapter} with a {@link Context}, {@link LayoutInflater} and
 * a {@link List} of {@link D} which you would like to display.
 *
 * <p>
 * This activity has an additional implementation of {@link #onBindViewHolder(RecyclerView.ViewHolder,
 * int, int)} which has the view type as parameter.
 * </p>
 *
 * @author Christian Bahl
 * @see RecyclerView.Adapter
 */
public abstract class CBAdapterRecyclerView<D, L extends List<D>> extends RecyclerView.Adapter {

  protected LayoutInflater inflater;
  protected Context context;
  protected L items;

  public CBAdapterRecyclerView(Context context) {
    this.context = context;
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  /**
   * Set the items
   *
   * @param items {@link L}
   */
  public void setItems(L items) {
    this.items = items;
  }

  /**
   * Get the items
   *
   * @return items {@link L}
   */
  public L getItems() {
    return items;
  }

  /**
   * Get item at position
   *
   * @param position position
   * @return item at position
   */
  public D getItem(int position) {
    return items.get(position);
  }

  /**
   * Add items
   *
   * <p>
   * If {@link #items} is null an empty {@link L} will be created first.
   * </p>
   *
   * @param items {@link L}
   */
  public void addNewItems(L items) {
    if (this.items == null) {
      this.items = createEmptyList();
    }

    this.items.addAll(0, items);
  }

  /**
   * Add a single item.
   *
   * <p>
   * If {@link #items} is null an empty {@link List} will be created first.
   * </p>
   *
   * @param item {@link D}
   */
  public void addNewItem(D item) {
    if (this.items == null) {
      this.items = createEmptyList();
    }

    this.items.add(item);
  }

  /**
   * Creates an empty {@link L}
   *
   * @return empty {@link L}
   */
  @SuppressWarnings("unchecked") private L createEmptyList() {
    return (L) new ArrayList<>();
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
