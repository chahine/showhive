![Build Status](https://img.shields.io/badge/build_status-todo-red.svg)
![minSdkVersion](https://img.shields.io/badge/minSdk-21-red.svg)
![compileSdkVersion](https://img.shields.io/badge/compileSdkVersion-27-green.svg)
![Version](https://img.shields.io/badge/version-alpha-blue.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

![ShowHive](base/src/main/res/mipmap-xxxhdpi/ic_launcher.png)

# Overview

Small Trakt client. WIP

# Project Setup

## Cloning

```
$ git clone git@github.com:chahinem/showhive.git
$ echo BUILD_NUMBER = 1 > version.properties
$ echo VERSION = 1 >> version.properties
```

## Required keys

```
# gradle.properties
TRAKT_CLIENT_ID     = 
TRAKT_CLIENT_SECRET = 
TMDB_API_KEY        = 
```

## Build

### Clean Build

```
$ ./gradlew clean build
```

### Release variant

TODO

### Code analysis

TODO

# Android Specs

### Libraries and Tools Overview
- [Support libraries](https://developer.android.com/topic/libraries/support-library/index.html)
- [Android Annotations](https://github.com/androidannotations/androidannotations/wiki)
- Rx Libraries
    - [RxAndroid](https://github.com/ReactiveX/RxAndroid): Android specific bindings for RxJava 2
    - [RxBinding](https://github.com/JakeWharton/RxBinding): RxJava binding APIs for Android UI widgets from the platform and support libraries
    - [RxJava](https://github.com/ReactiveX/RxJava): Java VM implementation of Reactive Extensions
- [Retrofit 2](http://square.github.io/retrofit/): A type-safe HTTP client for Android and Java
- [Timber](https://github.com/JakeWharton/timber): Logger with a small, extensible API

### Architecture

TODO

### Continuous Integration

TODO

## Contributing

1. Create your feature branch (`git checkout -b feature/name`)
2. Commit your changes (`git commit -am 'Feature - Name [Name](url)'`)
3. Publish the branch (`git push origin feature/name`)
4. Create a new Pull Request
5. Profit! :white_check_mark:

## Contributors

[![Chahine Mouhamad](https://avatars0.githubusercontent.com/u/3603230?s=100)](https://github.com/chahinem/)  
|:---:|
[Chahine Mouhamad](https://bitbucket.org/Chahine/) 

## License

    MIT License
    
    Copyright (c) 2018 Chahine
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
