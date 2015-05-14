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
package com.christianbahl.appkit.viewstate.kotlin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.christianbahl.appkit.viewstate.kotlin.R
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView

/**
 * @author Christian Bahl
 */
public abstract class CBActivityMvpToolbarFragmentViewState<CV : View, D, V : MvpLceView<D>, P : MvpPresenter<V>> : CBActivityMvpToolbarViewState<CV, D, V, P>() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().replace(R.id.contentView,
                                                             createFragmentToDisplay()).commit()
    }
  }

  override fun getLayoutRes(): Int {
    return R.layout.cb_activity_toolbar_fragment
  }

  /**
   * Returns the [Fragment] which should be displayed by this activity.
   *
   * @return [Fragment]
   */
  protected abstract fun createFragmentToDisplay(): Fragment
}
