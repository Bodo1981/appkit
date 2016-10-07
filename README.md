# appkit
This is an extension for the [mosby](https://github.com/sockeqwe/mosby) library from [Hannes Dorfmann](http://hannesdorfmann.com/).

It contains a few default activities and fragments which are often used in android development (e.g. Activity with Toolbar, Activity with Tabs, Fragment with RecyclerView, ...)

#### CBActivityFragment
Displays a simple activity with a container view for a fragment:

* **createFragmentToDisplay():** provide the fragment which should be displayed

#### CBActivityToolbar
Displays a simple activity with a toolbar on top.<br />
There is only one optional method you can override:

* **readExtras(Bundle extras)** read extras passed to activity

#### CBActivityToolbarFragment
Displays a simple activity with a toolbar which contains a fragment.<br />
Same as CBActivityToolbar plus one additional method:

* **createFragmentToDisplay():** provide the fragment which should be displayed

#### CBActivityMvpToolbar
Displays a simple mvp activity with a toolbar on top.<br/>
You have to implement only three methods:

* **createPresenter():** provide a presenter which loads the data
* **loadData(boolean isContentVisible):** load the data (e.g. presenter.loadListData); isContentVisible helps to distinguish between the first load (no data visible) and for example a pull to refresh or click on an error view (data already visible)
* **setData(D data):** is called when the presenter has loaded the data
 
There a are a few optional methods you can override if you need to:
* **readExtras(Bundle extras):** read extras passed to activity

#### CBActivityMvpToolbarTabs
Displays a simple mvp activity with a toolbar and tabs on top.<br/>
Same as CBActivityMvpToolbar plus one additional method:

* **createAdapter():** provide a PagerAdapter which is responsible for displaying the tabs

#### CBActivityMvpToolbarFragment
Displays a simple mvp activity with a toolbar which only contains a fragment.<br/>
Same as CBActivityMvpToolbar plus one additional method:

* **createFragmentToDisplay():** provide the fragment which should be displayed

#### CBFragmentMvpRecyclerView
A simple fragment with a recycler view.

* **createPresenter():** provide a presenter which loads the data
* **createAdapter():** provide a adapter which displays the data
* **loadData(boolean isContentVisible):** load the data (e.g. presenter.loadListData); isContentVisible helps to distinguish between the first load (no data visible) and for example a pull to refresh or click on an error view (data already visible)
* **setData(D data):** is called when the presenter has loaded the data

#### CBFragmentMvpRecyclerViewPtr
Excactly the same as CBFragmentMvpRecyclerView but with pull to refresh functionallity.<br/>
The default behavior for pull to refresh is to call the **loadData(true)** function but this can be overriden in **onRefreshStarted()**

All this activities and fragments also have a viewstate implementation. For further information see [Mosby Framework](http://hannesdorfmann.com/mosby/)

# Additional stuff

#### CBAdapterRecyclerView
A simple RecyclerView.Adapter with a few helper methods to add, delete items. It also has a setter and getter for the data.

* **onCreateViewHolder(ViewGroup parent, int viewType):** create the viewholder
* **onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, int viewType):** bind the viewholder
* **getItemViewType(int position):** (optional) only needed if you have different view types

#### CBAdapterRecyclerViewParallax
Same as CBAdapterRecyclerView with an extra parallax effect to the specified view types

* **isItemParallaxScrollable(int position, int viewType)**: here you can say which item should have the parallax effect by position and/or view type
* **doParallaxScrolling(RecyclerView.ViewHolder viewHolder, int position, int viewType, int pixelAlreadyScrolledOut):**: do the parallax effect

# Dependency

Newest Version (Jcenter):

[ ![Download](https://api.bintray.com/packages/bodo1981/maven/appkit/images/download.svg) ](https://bintray.com/bodo1981/maven/appkit/_latestVersion)

    dependencies {
        compile 'com.christianbahl.appkit:core:2.0.29'
        compile 'com.christianbahl.appkit:viewstate:2.0.29'
        compile 'com.christianbahl.appkit:rx:2.0.29'
        compile 'com.christianbahl.appkit:rx2:2.0.29'
    }

#Changelog

The changelog can be found in the [release section](https://github.com/Bodo1981/appkit/releases)

#License

    Copyright 2015 Christian Bahl

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
