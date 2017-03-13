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
package com.christianbahl.appkit.core.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import com.christianbahl.appkit.core.common.view.CBMvpView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import java.util.List;

/**
 * <p>
 * A fragment which uses the Model-View-Presenter architecture.
 * </p>
 *
 * <p>
 * You have to specify a {@link SwipeRefreshLayout} with the id <code>R.layout.pullToRefresh</code>.
 * After the refresh is started the function {@link #onRefreshStarted()} is called. In the default
 * implementation {@link #loadData(boolean)} is called but you can override this if you need to.
 * </p>
 *
 * <p>
 * Some simplifications are added to handle {@link List} data for the {@link RecyclerView}, e.g
 * you only have to define the data of the list in generics and not always the list.
 * </p>
 *
 * @author Christian Bahl
 * @see CBFragmentMvpRecyclerViewPtr
 */
public abstract class CBFragmentMvpListRecyclerViewPtr<M, V extends CBMvpView<M>, P extends MvpPresenter<V>, A extends RecyclerView.Adapter>
    extends CBFragmentMvpRecyclerViewPtr<List<M>, V, P, A> implements CBMvpView<M> {

}
