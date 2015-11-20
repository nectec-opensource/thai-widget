# Android-Thai-Widget [![Build Status](https://travis-ci.org/nectec-wisru/android-ThaiWidget.svg?branch=master)](https://travis-ci.org/nectec-wisru/android-ThaiWidget)

Thai style android custom widget by thai-dev for thai-dev

Widget
-------
* CitizenIdEditText - EditText for Citizen ID with validator [Released]
* HouseIdEditText - EditText for House ID with validator [Released]
* DatePicker - Buddism year version of android DatePicker [TODO]
* AreaPicker - for input area size in Rai Ngan Wa2 formate [Beta Release]
* AddressPicker - for select Thai's Province District and Subdistrict [WIP]

Download
-------
### JCenter

Add JCenter to your build file's list of repositories.

    repositories {
        jcenter()
    }

Add dependencies on app module

    dependencies {
        ...
        compile 'th.or.nectec.android:thai-widget:0.2.1'
        ...
    }


License
--------

    Copyright © 2015 NECTEC
      National Electronics and Computer Technology Center, Thailand

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    

[![NECTEC](http://www.nectec.or.th/themes/nectec/img/logo.png)](https://www.nectec.or.th)

Publishing to JCenter
---------
The follow instructions only for Project Administrator

1. Configure gradle.properties with:
    
        bintrayKey = <YOUR API KEY>
        bintrayUser = <YOUR BINTRAY USERNAME>

2. Upload with command :

        ./gradlew bintrayUpload
    
3. Log in to Bintray to publish

    

