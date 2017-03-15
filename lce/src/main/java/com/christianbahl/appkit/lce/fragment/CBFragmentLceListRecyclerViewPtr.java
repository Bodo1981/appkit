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
package com.christianbahl.appkit.lce.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import java.util.List;

/**
 * <p>
 * A fragment which uses the Model-View-Presenter architecture and displays a list of items in a {@link RecyclerView} with
 * {@link SwipeRefreshLayout}.
 * </p>
 *
 * @author Christian Bahl
 * @see CBFragmentLceRecyclerViewPtr
 */
public abstract class CBFragmentLceListRecyclerViewPtr<M, V extends MvpLceView<List<M>>, P extends MvpPresenter<V>, A extends RecyclerView.Adapter>
    extends CBFragmentLceRecyclerViewPtr<List<M>, V, P, A> implements MvpLceView<List<M>> {

}
