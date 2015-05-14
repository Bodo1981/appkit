# appkit

This is an extension for the mosby library from Hannes Dorfmann.

It contains a few default activities and fragments which are often used in android development (e.g. Activity with Toolbar, Activity with Tabs, Fragment with RecyclerView, ...)

# Dependency

Newest Version (Jitpack.io):

[![Release](https://img.shields.io/github/release/Bodo1981/appkit.svg?label=maven)]
(https://jitpack.io/#Bodo1981/appkit)

    dependencies {
        // complete library
        compile 'com.github.Bodo1981:appkit:1.0.9'

        // or submodules
        compile 'com.github.Bodo1981.appkit:core:1.0.9'
        compile 'com.github.Bodo1981.appkit:viewstate:1.0.9'
        compile 'com.github.Bodo1981.appkit:core-kotlin:1.0.9'
        compile 'com.github.Bodo1981.appkit:viewstate-kotlin:1.0.9'
    }

The library contains submodules written in java and kotlin. Functionality is the same.
 
    buildscript {
        repositories {
            jcenter()
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:1.1.3'
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
        }
    }

    dependencies {
        apt 'frankiesardo:icepick-processor:3.0.2'
        apt 'com.hannesdorfmann.fragmentargs:processor:2.1.0'
    }

#Changelog

The changelog can be found in the [release section](https://github.com/Bodo1981/appkit/releases)
