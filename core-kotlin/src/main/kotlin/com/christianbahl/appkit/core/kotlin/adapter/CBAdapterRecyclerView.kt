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
package com.christianbahl.appkit.core.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import java.util.ArrayList

/**
 * A base [RecyclerView.Adapter] with a [Context], [LayoutInflater] and
 * a [List] of [M] which you would like to display.
 *
 * This activity has an additional implementation of [onBindViewHolder] which has the view type as parameter.
 *
 * @author Christian Bahl
 * @see RecyclerView.Adapter
 */
public abstract class CBAdapterRecyclerView<M : Any>(
    protected var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  protected var inflater: LayoutInflater
  protected var items: MutableList<M>? = null

  init {
    this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
  }

  /**
   * Get item at position
   *
   * @param position position
   * @return item at position
   */
  public fun getItem(position: Int): M? {
    return items?.get(position)
  }

  /**
   * Add items
   *
   * If [items] is null an empty [MutableList] will be created first.
   *
   * @param items [MutableList]
   */
  public fun addNewItems(items: MutableList<M>) {
    this.items = this.items ?: createEmptyList()

    this.items?.addAll(items)
  }

  /**
   * Add a single item.
   *
   * If [items] is null an empty [List] will be created first.
   *
   * @param item [M]
   */
  public fun addNewItem(item: M) {
    this.items = this.items ?: createEmptyList()

    this.items?.add(item)
  }

  /**
   * Creates an empty [MutableList]
   * @return empty [MutableList]
   */
  [suppress("UNCHECKED_CAST")] private fun createEmptyList(): MutableList<M> {
    return ArrayList<Any>() as MutableList<M>
  }

  override fun getItemCount(): Int {
    return items?.size() ?: 0
  }

  override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
    onBindViewHolder(viewHolder, position, getItemViewType(position))
  }

  /**
   * Bind the [RecyclerView.ViewHolder]
   *
   * @param viewHolder [RecyclerView.ViewHolder]
   * @param position position
   * @param viewType view type
   */
  public abstract fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int,
      viewType: Int)
}