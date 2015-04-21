package com.christianbahl.appkit.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.christianbahl.appkit.R
import com.christianbahl.appkit.adapter.CBAdapterRecyclerView
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView

/**
 * A fragment which uses the Model-View-Presenter architecture.
 *
 * The content view is a [RecyclerView] with the id `R.layout.contentView`
 *
 * You have to implement the [A] for the [RecyclerView] in [.createAdapter].
 *
 * @author Christian Bahl
 * @see MvpLceFragment
 */
public abstract class CBFragmentMvpRecyclerView<AD, D, V : MvpLceView<D>, P : MvpPresenter<V>, A : CBAdapterRecyclerView<AD, MutableList<AD>>> : MvpLceFragment<RecyclerView, D, V, P>() {

    protected var adapter: A? = null
    protected var emptyView: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = createAdapter()
        if (adapter == null) {
            throw IllegalStateException(
                    "Adapter is null. Did you forget to return an adapter in #createAdapter()?")
        }

        contentView.setAdapter(adapter)

        emptyView = view.findViewById(R.id.emptyView)
        if (emptyView == null) {
            throw IllegalStateException(
                    "Empty View is null. Do you have a View with id = R.id.emptyView in your xml layout?")
        }

        val layoutManager = createRecyclerViewLayoutManager()
        if (layoutManager == null) {
            throw IllegalStateException(
                    "The RecyclerView.LayoutManager is not specified. You have to provide a " + "RecyclerView.LayoutManager by #createRecyclerViewLayoutManager()")
        } else {
            contentView.setLayoutManager(layoutManager)
        }

        onMvpViewCreated(view, savedInstanceState)

        loadData(false)
    }

    override fun showContent() {
        super.showContent()

        if (adapter!!.getItemCount() == 0) {
            emptyView!!.setVisibility(View.VISIBLE)
        } else {
            emptyView!!.setVisibility(View.GONE)
        }
    }

    override fun showLoading(isContentVisible: Boolean) {
        super.showLoading(isContentVisible)

        if (isContentVisible && emptyView!!.getVisibility() == View.VISIBLE) {
            emptyView!!.setVisibility(View.GONE)
        }
    }

    override fun showError(e: Throwable, isContentVisible: Boolean) {
        super.showError(e, isContentVisible)

        if (isContentVisible && emptyView!!.getVisibility() == View.VISIBLE) {
            emptyView!!.setVisibility(View.GONE)
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.cb_fragment_recycler_view
    }

    override fun getErrorMessage(throwable: Throwable, isContentVisible: Boolean): String? {
        return throwable.getMessage()
    }

    /**
     * Creates the [RecyclerView.LayoutManager].
     * Default: [LinearLayoutManager]
     *
     * @return [RecyclerView.LayoutManager]
     */
    protected fun createRecyclerViewLayoutManager(): RecyclerView.LayoutManager? {
        return LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false)
    }

    /**
     * Creates the [A].
     * Called in [.onViewCreated]
     *
     * @return [A]
     */
    protected abstract fun createAdapter(): A

    /**
     * Called after the mvp views and the recycler view are created
     *
     * @param view [View]
     * @param savedInstanceState [Bundle]
     */
    protected abstract fun onMvpViewCreated(view: View, savedInstanceState: Bundle?)
}