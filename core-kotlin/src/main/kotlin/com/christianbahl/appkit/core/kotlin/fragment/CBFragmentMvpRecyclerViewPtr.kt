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
package com.christianbahl.appkit.core.kotlin.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import com.christianbahl.appkit.core.kotlin.R
import com.christianbahl.appkit.core.kotlin.adapter.CBAdapterRecyclerView
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import kotlin.properties.Delegates

/**
 * A fragment which uses the Model-View-Presenter architecture.
 *
 * You have to specify a [SwipeRefreshLayout] with the id `R.layout.pull_to_refresh`.
 * After the refresh is started the function [onRefreshStarted] is called. In the default
 * implementation [loadData] is called but you can override this if you need to.
 *
 * @author Christian Bahl
 * @see CBFragmentMvpRecyclerView
 */
public abstract class CBFragmentMvpRecyclerViewPtr<M: Any, V : MvpLceView<M>, P : MvpPresenter<V>, A : CBAdapterRecyclerView<M, MutableList<M>>> : CBFragmentMvpRecyclerView<M, V, P, A>() {

  protected var swipeRefreshLayout: SwipeRefreshLayout by Delegates.notNull()

  override fun getLayoutRes(): Int {
    return R.layout.cb_fragment_recycler_view_ptr
  }

  override fun onMvpViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onMvpViewCreated(view, savedInstanceState)

    swipeRefreshLayout = view.findViewById(R.id.pullToRefresh) as SwipeRefreshLayout

    swipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
      override fun onRefresh() {
        onRefreshStarted()
      }
    })
  }

  override fun showContent() {
    super.showContent()

    swipeRefreshLayout.setVisibility(View.VISIBLE)
    swipeRefreshLayout.setRefreshing(false)
  }

  override fun showLoading(isContentVisible: Boolean) {
    super.showLoading(isContentVisible)

    if (!isContentVisible) {
      swipeRefreshLayout.setVisibility(View.GONE)
    }
  }

  override fun showError(e: Throwable, isContentVisible: Boolean) {
    super.showError(e, isContentVisible)

    swipeRefreshLayout.setVisibility(View.GONE)
    swipeRefreshLayout.setRefreshing(false)
  }

  /**
   * Called from the [SwipeRefreshLayout.OnRefreshListener].
   * Default: call of [loadData]
   */
  protected open fun onRefreshStarted() {
    loadData(true)
  }
}