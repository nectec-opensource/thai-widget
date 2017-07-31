# Android-Thai-Widget
[![Build Status](https://travis-ci.org/nectec-opensource/thai-widget.svg?branch=master)](https://travis-ci.org/nectec-opensource/thai-widget)
[![Download](https://api.bintray.com/packages/nectec-wisru/maven/thai-widget/images/download.svg)](https://bintray.com/nectec-wisru/maven/thai-widget/_latestVersion)

Thai style android custom widget by thai-dev for thai-dev

## Widget

### CitizenIdEditText
EditText for Citizen ID with validator

![CitizenIdEditText](https://raw.githubusercontent.com/nectec-wisru/android-ThaiWidget/77c2a32fb9d21fcbbbed4f40ae29205384bb3ba1/images/citizen_id.gif)

### HouseIdEditText
EditText for House ID with validator

![CitizenIdEditText](https://raw.githubusercontent.com/nectec-wisru/android-ThaiWidget/77c2a32fb9d21fcbbbed4f40ae29205384bb3ba1/images/house_id.gif)

### AreaPicker
For input area size in Rai Ngan Wa2 format

![CitizenIdEditText](https://raw.githubusercontent.com/nectec-wisru/android-ThaiWidget/77c2a32fb9d21fcbbbed4f40ae29205384bb3ba1/images/area_picker.gif)

### AddressPicker
Selector of Thai's Province District and SubDistrict

![CitizenIdEditText](https://raw.githubusercontent.com/nectec-wisru/android-ThaiWidget/77c2a32fb9d21fcbbbed4f40ae29205384bb3ba1/images/address_picker.gif)

### DatePicker
Buddha year version of android DatePicker

![CitizenIdEditText](https://raw.githubusercontent.com/nectec-wisru/android-ThaiWidget/77c2a32fb9d21fcbbbed4f40ae29205384bb3ba1/images/date_picker.gif)

## Download

### JCenter

Add JCenter to your build file's list of repositories.

```gradle
repositories {
    jcenter()
}
```

Add dependencies on app module

```gradle
dependencies {
    ...
    compile 'th.or.nectec.android:thai-widget:0.4.6'
    ...
}
```

### Proguard-Rule

Add follow this in proguard-rules.pro in your project.

```proguard
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class th.or.nectec.thai.** { *; }
```

## Libraries
This project depend on

- [gson](https://github.com/google/gson)

Test libraries use on this project

- [JUnit](http://junit.org/)
- [Robolectric](http://robolectric.org/)
- [Espresso](https://google.github.io/android-testing-support-library/)

## Code Quality

This project use quality control by unit-test ui-test and code analysis tools.

Static code analysis tools

- [Checkstyle](http://checkstyle.sourceforge.net/)
- [PMD](https://pmd.github.io/)
- [Findbugs](http://findbugs.sourceforge.net/)
- [Android Check](https://github.com/noveogroup/android-check)

check for config of those tools at [Java Quality Config](https://github.com/Blazei/java-quality-config)

## Develop by

- [Blaze Piruin](https://github.com/Blazei)
- [N. Choatravee](https://github.com/chncs23)

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
