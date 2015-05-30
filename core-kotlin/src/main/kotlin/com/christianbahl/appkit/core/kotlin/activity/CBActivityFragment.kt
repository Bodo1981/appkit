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
package com.christianbahl.appkit.core.kotlin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.christianbahl.appkit.core.kotlin.R
import com.hannesdorfmann.mosby.MosbyActivity

/**
 * An activity which uses the Model-View-Presenter architecture.
 *
 * The layout must have a [android.view.ViewGroup] for the [Fragment] with the id `R.layout.contentView`
 *
 * The standard layout implements all necessary views. You can override the default layout in [getLayoutRes]. But be careful, you have to provide the necessary views!
 *
 * You have to override the [createFragmentToDisplay] to create the [Fragment] which should be displayed.
 *
 * @author Christian Bahl
 * @see MosbyActivity
 */
public abstract class CBActivityFragment : MosbyActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(getLayoutRes())

    if (getIntent() != null && getIntent().getExtras() != null) {
      readExtras(getIntent().getExtras())
    }

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().replace(R.id.contentView,
                                                             createFragmentToDisplay()).commit()
    }
  }

  /**
   * Provide the layout res id for the activity.
   *
   * @return layout res id
   */
  protected fun getLayoutRes(): Int {
    return R.layout.cb_activity_fragment
  }

  /**
   * Handle extra bundle data
   *
   * @param bundle bundle with extras passed to activity
   */
  protected fun readExtras(bundle: Bundle) {

  }

  /**
   * Returns the [Fragment] which should be displayed by this activity.
   *
   * @return [Fragment]
   */
  protected abstract fun createFragmentToDisplay(): Fragment
}