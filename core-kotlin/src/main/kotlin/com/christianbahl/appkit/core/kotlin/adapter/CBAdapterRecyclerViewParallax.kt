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
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * A [CBAdapterRecyclerView] with a parallax effect.
 *
 * Specify the view types that should get the parallax effect in [isItemParallaxScrollable]
 * and do the parallax effect in [doParallaxScrolling]
 *
 * @author Christian Bahl
 * @see RecyclerView.Adapter
 */
public abstract class CBAdapterRecyclerViewParallax<M>(context: Context) : CBAdapterRecyclerView<M>(
    context) {

  public var additionalOnScrollListener: RecyclerView.OnScrollListener? = null

  private var lastViewHolderPosition: Int = 0
  private var lastViewType: Int = 0
  private var lastTopView: View? = null

  constructor(context: Context, recyclerView: RecyclerView) : this(context) {
    addParallaxScrollListenerToRecyclerView(recyclerView)
  }

  /**
   * Adds the [CBRecyclerViewParallaxListener] to the recycler view
   *
   * @param recyclerView recycler view
   */
  public fun addParallaxScrollListenerToRecyclerView(recyclerView: RecyclerView) {
    recyclerView.addOnScrollListener(CBRecyclerViewParallaxListener())
  }

  /**
   * This method gets called to check if the item at a position and view type has parallax effect
   *
   * @param position position
   * @param itemViewType item view type
   * @return result if has parallax effect (true) otherwise (false)
   */
  public abstract fun isItemParallaxScrollable(position: Int, itemViewType: Int): Boolean

  /**
   * This method gets called to do the real parallax scrolling. So implement
   * here your parallax scrolling implementation by manipulating the view
   *
   * @param viewHolder [RecyclerView.ViewHolder] on top of the screen
   * @param position The position (index) in the adapters dataset
   * @param viewType The viewType
   * @param pixelAlreadyScrolledOut The number of pixels, that are already scrolled out or in on top of the recyclerview
   */
  public abstract fun doParallaxScrolling(viewHolder: RecyclerView.ViewHolder, position: Int,
                                          viewType: Int, pixelAlreadyScrolledOut: Int)

  private inner class CBRecyclerViewParallaxListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

      try {
        if (recyclerView!!.getLayoutManager() is LinearLayoutManager) {
          // reset parallax
          if (isItemParallaxScrollable(lastViewHolderPosition, lastViewType)) {
            if (lastViewHolderPosition != recyclerView.getChildLayoutPosition(
                recyclerView.getChildAt(0))) {
              val vh = recyclerView.getChildViewHolder(lastTopView)
              if (vh != null) {
                doParallaxScrolling(vh, lastViewHolderPosition, lastViewType, 0)
              }
            }
          }

          lastTopView = recyclerView.getChildAt(0)
          lastViewHolderPosition = recyclerView.getChildLayoutPosition(lastTopView)
          lastViewType = recyclerView.findViewHolderForLayoutPosition(
              lastViewHolderPosition).getItemViewType()

          val viewHolder = recyclerView.findViewHolderForLayoutPosition(lastViewHolderPosition)

          if (viewHolder != null) {
            if (isItemParallaxScrollable(viewHolder.getLayoutPosition(),
                                         viewHolder.getItemViewType())) {
              doParallaxScrolling(viewHolder, viewHolder.getLayoutPosition(),
                                  viewHolder.getItemViewType(),
                                  -recyclerView.getChildAt(0).getTop())
            }
          }

          if (additionalOnScrollListener != null) {
            additionalOnScrollListener!!.onScrolled(recyclerView, dx, dy)
          }
        }
      } catch (e: Exception) {
        e.printStackTrace()
      }

    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
      if (additionalOnScrollListener != null) {
        additionalOnScrollListener!!.onScrollStateChanged(recyclerView, newState)
      }
    }
  }
}