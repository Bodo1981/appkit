# appkit
This is an extension for the [mosby](https://github.com/sockeqwe/mosby) library from [Hannes Dorfmann](http://hannesdorfmann.com/).

It contains a few default activities and fragments which are often used in android development (e.g. Activity with Toolbar, Activity with Tabs, Fragment with RecyclerView, ...)

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
* **onMvpViewCreated():** called after all views are set up correctly
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

All this activities and fragments also have a viewstate implementation. For further information see [Mosby Framework](http://hannesdorfmann.com/android/mosby/)

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

#### CBButterknifeViewHolder
A RecyclerView.ViewHolder that automatically enables Butterknife. You only have to annotate your views with the butterknife annotations

# Dependency

Newest Version (Jitpack.io):

[![Release](https://img.shields.io/github/release/Bodo1981/appkit.svg?label=maven)]
(https://jitpack.io/#Bodo1981/appkit)

    dependencies {
        // complete library
        compile 'com.github.Bodo1981:appkit:1.2.3'

        // or submodules (java)
        compile 'com.github.Bodo1981.appkit:core:1.2.3'
        compile 'com.github.Bodo1981.appkit:viewstate:1.2.3'
        
        // or submodules (kotlin)
        compile 'com.github.Bodo1981.appkit:core-kotlin:1.2.3'
        compile 'com.github.Bodo1981.appkit:viewstate-kotlin:1.2.3'
    }

The library contains submodules written in java and kotlin. Functionality is the same.
 
    buildscript {
        repositories {
            jcenter()
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:1.2.3'
            classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
        }
    }

    allprojects {
        repositories {
            jcenter()
            maven {
                // for icepick
                url 'https://clojars.org/repo/'
            }
            maven {
                // for appkit
                url 'https://jitpack.io'
            }
            maven {
                // for material design colors
                url 'http://raw.github.com/wada811/Android-Material-Design-Colors/master/repository/'
            }
        }
    }

    dependencies {
        apt 'frankiesardo:icepick-processor:3.0.2'
        apt 'com.hannesdorfmann.fragmentargs:processor:2.1.0'
    }

#Changelog

The changelog can be found in the [release section](https://github.com/Bodo1981/appkit/releases)
