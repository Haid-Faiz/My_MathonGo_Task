# My_MathonGo_Task
[![Build](https://github.com/Haid-Faiz/My_MathonGo_Task/actions/workflows/build_ci.yml/badge.svg)](https://github.com/Haid-Faiz/My_MathonGo_Task/actions/workflows/build_ci.yml)
[![Kolint Lint](https://github.com/Haid-Faiz/My_MathonGo_Task/actions/workflows/kotlin_lint_ci.yml/badge.svg)](https://github.com/Haid-Faiz/My_MathonGo_Task/actions/workflows/kotlin_lint_ci.yml)
[![Unit Tests](https://github.com/Haid-Faiz/My_MathonGo_Task/actions/workflows/unit_tests_ci.yml/badge.svg)](https://github.com/Haid-Faiz/My_MathonGo_Task/actions/workflows/unit_tests_ci.yml)
[![Platform](https://img.shields.io/badge/platform-android-blue.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-23%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=23)


### Developed this app as a Task for Android Developer Internship at MathonGo.

**Firstly app loads the data form server through an api call Using Retrofit, then shows it in UI as Un Attempted questions by rendering them from LaTeX format.
Then user can start attempting questions on clicking them, All the attempted questions stored locally using Room Database and can be seen (without internet connection also) by applying a filter as Attempted questions.** 


***You can Install the latest apk from below 👇***

[![MathonGoTask App](https://img.shields.io/badge/APK-latest-%231a79d9?style=for-the-badge&logo=android)](https://github.com/Haid-Faiz/My_MathonGo_Task/releases/download/task_apk/app-release.apk)


## App Look
<table>
   <tr>
<td><img src = "https://user-images.githubusercontent.com/56159740/144766066-86e1c6ce-08a5-419f-9887-7eb4b8f1f0e9.gif" height = "380" width="200"></td>
<td><img src = "https://user-images.githubusercontent.com/56159740/144766073-3a5ec7f7-436b-49fb-8bf3-892566d3e6e2.gif" height = "380" width="200"></td>
<td><img src = "https://user-images.githubusercontent.com/56159740/144766156-031842e6-e7f6-4dc1-8cf7-02f7999b4fee.gif" height = "380" width="200"></td>
  </tr>
</table>


## About

- This project uses [this](https://github.com/lingarajsankaravelu/Katex) library to render Questions, etc which are coming in LaTeX format.
- Clean and Simple Material UI.

- **This project follows the MVVM structure with Modular Architecture.**

  There are two _modules_ in the project

  `app` - The UI of the app. The main project that forms the APK.
  
  `datastore` - The REST API consumption & Local Database (ROOM DB) android library

### Developed with
- [Katex](https://github.com/lingarajsankaravelu/Katex) - Android Katex library to render math Formula faster in android using khanacademy Katex.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Room Database](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library. 
- [Dagger-Hilt](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Jetpack Navigation](https://developer.android.com/guide/navigation) - Android Jetpack's Navigation component helps you implement navigation, from simple button clicks to more complex patterns.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [Coil-kt](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.
- [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to
 store key-value pairs. Basically it's a replacement for SharedPreferences.



