package com.christianbahl.appkit.core.kotlin.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.christianbahl.appkit.core.kotlin.R
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import kotlin.properties.Delegates

/**
 * An activity which uses the Model-View-Presenter architecture and adds a [Toolbar] on top.
 *
 * This activity also enables [android.support.v7.app.ActionBar.setDisplayShowTitleEnabled] so the
 * toolbar will show the title. If you don´t want this in your activity you can override this
 * in [.isDisplayShowTitleEnabled].
 *
 * The standard layout implements all necessary views. You can override the default layout in
 * [.getLayoutRes]. But be careful, you have to provide the necessary views!
 *
 * @author Christian Bahl
 * @see MvpLceActivity
 */
public abstract class CBActivityMvpToolbar<CV : View, D, V : MvpLceView<D>, P : MvpPresenter<V>> : MvpLceActivity<CV, D, V, P>() {

  protected var toolbar: Toolbar by Delegates.notNull()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(getLayoutRes())
  }

  override fun onContentChanged() {
    super.onContentChanged()

    // throws an exception if toolbar is not a toolbar
    toolbar = findViewById(R.id.toolbar) as Toolbar

    setSupportActionBar(toolbar)
    getSupportActionBar().setDisplayShowTitleEnabled(isDisplayShowTitleEnabled())

    onMvpViewCreated()

    loadData(false)
  }

  override fun getErrorMessage(throwable: Throwable, isContentVisible: Boolean): String? {
    return throwable.getMessage()
  }

  /**
   * Should the title be displayed in the toolbar.
   *
   * @return `true` if title should be displayed in toolbar otherwise `false`
   */
  protected open fun isDisplayShowTitleEnabled(): Boolean {
    return true
  }

  /**
   * Provide the layout res id for the activity.
   *
   * @return layout res id
   */
  protected open fun getLayoutRes(): Int {
    return R.layout.cb_activity_toolbar_fragment
  }

  /**
   * Called after mvp views and toolbar are created
   */
  protected open fun onMvpViewCreated() {

  }
}