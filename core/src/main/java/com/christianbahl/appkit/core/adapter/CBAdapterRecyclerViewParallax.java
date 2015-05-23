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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <p>
 * A {@link CBAdapterRecyclerView} with a parallax effect.
 * </p>
 *
 * <p>
 * Specify the view types that should get the parallax effect in {@link
 * #isItemParallaxScrollable(int, int)} and do the parallax effect in {@link
 * #doParallaxScrolling(RecyclerView.ViewHolder, int, int, int)}
 * </p>
 *
 * @author Christian Bahl
 * @see RecyclerView.Adapter
 */
public abstract class CBAdapterRecyclerViewParallax<M> extends CBAdapterRecyclerView<M> {

  private RecyclerView.OnScrollListener additionalOnScrollListener;

  private int lastViewHolderPosition;
  private int lastViewType;
  private View lastTopView;

  public CBAdapterRecyclerViewParallax(Context context) {
    super(context);
  }

  /**
   * Set an additional onScrollListener for the recyclerview
   *
   * @param additionalOnScrollListener additional onScrollListener
   */
  public void setAdditionalOnScrollListener(
      RecyclerView.OnScrollListener additionalOnScrollListener) {
    this.additionalOnScrollListener = additionalOnScrollListener;
  }

  /**
   * Get the additional onScrollListener
   *
   * @return additional onScrollListener
   */
  public RecyclerView.OnScrollListener getAdditionalOnScrollListener() {
    return additionalOnScrollListener;
  }

  /**
   * This method gets called to check if the item at a position and view type has parallax effect
   *
   * @param position position
   * @param itemViewType item view type
   * @return result if has parallax effect (true) otherwise (false)
   */
  public abstract boolean isItemParallaxScrollable(int position, int itemViewType);

  /**
   * This method gets called to do the real parallax scrolling. So implement
   * here your parallax scrolling implementation by manipulating the view
   *
   * @param viewHolder {@link RecyclerView.ViewHolder} on top of the screen
   * @param position The position (index) in the adapters dataset
   * @param viewType The viewType
   * @param pixelAlreadyScrolledOut The number of pixels, that are already scrolled out or in on
   * top of the listview
   */
  public abstract void doParallaxScrolling(RecyclerView.ViewHolder viewHolder, int position,
      int viewType, int pixelAlreadyScrolledOut);

  private class RecyclerViewParallaxListener extends RecyclerView.OnScrollListener {
    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

      try {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
          // reset parallax
          if (isItemParallaxScrollable(lastViewHolderPosition, lastViewType)) {
            if (lastViewHolderPosition != recyclerView.getChildLayoutPosition(
                recyclerView.getChildAt(0))) {
              RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(lastTopView);
              if (vh != null) {
                doParallaxScrolling(vh, lastViewHolderPosition, lastViewType, 0);
              }
            }
          }

          lastTopView = recyclerView.getChildAt(0);
          lastViewHolderPosition = recyclerView.getChildLayoutPosition(lastTopView);
          lastViewType = recyclerView.findViewHolderForLayoutPosition(lastViewHolderPosition)
              .getItemViewType();

          RecyclerView.ViewHolder viewHolder =
              recyclerView.findViewHolderForLayoutPosition(lastViewHolderPosition);

          if (viewHolder != null) {
            if (isItemParallaxScrollable(viewHolder.getLayoutPosition(),
                viewHolder.getItemViewType())) {
              doParallaxScrolling(viewHolder, viewHolder.getLayoutPosition(),
                  viewHolder.getItemViewType(), -recyclerView.getChildAt(0).getTop());
            }
          }

          if (additionalOnScrollListener != null) {
            additionalOnScrollListener.onScrolled(recyclerView, dx, dy);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      if (additionalOnScrollListener != null) {
        additionalOnScrollListener.onScrollStateChanged(recyclerView, newState);
      }
    }
  }
}
