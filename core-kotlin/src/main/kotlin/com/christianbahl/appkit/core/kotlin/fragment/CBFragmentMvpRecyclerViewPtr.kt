package com.christianbahl.appkit.core.kotlin.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import com.christianbahl.appkit.core.kotlin.R
import com.christianbahl.appkit.core.kotlin.adapter.CBAdapterRecyclerView
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView

/**
 * A fragment which uses the Model-View-Presenter architecture.
 *
 * You have to specify a [SwipeRefreshLayout] with the id `R.layout.pull_to_refresh`.
 * After the refresh is started the function [.onRefreshStarted] is called. In the default
 * implementation [.loadData] is called but you can override this if you need to.
 *
 * @author Christian Bahl
 * @see CBFragmentMvpRecyclerView
 */
public abstract class CBFragmentMvpRecyclerViewPtr<AD, D, V : MvpLceView<D>, P : MvpPresenter<V>, A : CBAdapterRecyclerView<AD, MutableList<AD>>> : CBFragmentMvpRecyclerView<AD, D, V, P, A>() {

  protected var swipeRefreshLayout: SwipeRefreshLayout? = null

  override fun getLayoutRes(): Int {
    return R.layout.cb_fragment_recycler_view_ptr
  }

  override fun onMvpViewCreated(view: View, savedInstanceState: Bundle?) {
    swipeRefreshLayout = view.findViewById(R.id.pullToRefresh) as SwipeRefreshLayout

    swipeRefreshLayout!!.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
      override fun onRefresh() {
        onRefreshStarted()
      }
    })
  }

  override fun showContent() {
    super.showContent()

    swipeRefreshLayout!!.setVisibility(View.VISIBLE)
    swipeRefreshLayout!!.setRefreshing(false)
  }

  override fun showLoading(isContentVisible: Boolean) {
    super.showLoading(isContentVisible)

    if (!isContentVisible) {
      swipeRefreshLayout!!.setVisibility(View.GONE)
    }
  }

  override fun showError(e: Throwable, isContentVisible: Boolean) {
    super.showError(e, isContentVisible)

    swipeRefreshLayout!!.setVisibility(View.GONE)
    swipeRefreshLayout!!.setRefreshing(false)
  }

  /**
   * Called from the [SwipeRefreshLayout.OnRefreshListener].
   * Default: call of [.loadData]
   */
  protected fun onRefreshStarted() {
    loadData(true)
  }
}