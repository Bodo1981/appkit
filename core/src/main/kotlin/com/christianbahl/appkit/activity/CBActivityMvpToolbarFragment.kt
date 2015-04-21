package com.christianbahl.appkit.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.christianbahl.appkit.R
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView

/**
 * An activity which uses the Model-View-Presenter architecture. It also adds a
 * [android.support.v7.widget.Toolbar] on top and has a container for the [Fragment].
 *
 * The layout must have a [android.view.ViewGroup] for the [Fragment] with the id
 * `R.layout.contentView`
 *
 * The standard layout implements all necessary views. You can override the default layout in
 * [.getLayoutRes]. But be careful, you have to provide the necessary views!
 *
 * You have to override the [.createFragmentToDisplay] to create the [Fragment] which
 * should be displayed.
 *
 * @author Christian Bahl
 * @see CBActivityMvpToolbar
 */
public abstract class CBActivityMvpToolbarFragment<CV : View, D, V : MvpLceView<D>, P : MvpPresenter<V>> : CBActivityMvpToolbar<CV, D, V, P>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentView,
                                                                   createFragmentToDisplay()).commit()
        }
    }

    override protected fun getLayoutRes(): Int {
        return R.layout.cb_activity_toolbar_fragment
    }

    override fun onMvpViewCreated() {
        // nothing to do here
    }

    /**
     * Returns the [Fragment] which should be displayed by this activity.
     *
     * @return [Fragment]
     */
    protected abstract fun createFragmentToDisplay(): Fragment
}