## WeatherApp App with Jetpack Compose and Repository Design Pattern 🚀

An Android app built with Jetpack Compose and Kotlin, using the awesome Repository design pattern 🤘. 

We are taking current location with FUSED LOCATION PROVIDER, from that we grab weather data from WEB API via Retrofit2 than storing it on ROOM DB, 
and if location request failed we retrieve from DB.

## Cool Tech Stack!

- [Kotlin](https://developer.android.com/kotlin) - The futuristic programming language that can run on JVM! It's the officially supported programming language for Android Studio and the community is
  moving rapidly from Java to Kotlin.
- [Android KTX](https://developer.android.com/kotlin/ktx.html) - Concise and idiomatic Kotlin library to Jetpack and Android platform APIs. It's like magic!
- [AndroidX](https://developer.android.com/jetpack/androidx) - The supercharged version of Android Support Library that's way better!
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - With great power comes great responsibility. It's designed to perform actions based on lifecycle changes of
  components like activities and fragments.
- [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) -The ViewModel class is like a superhero that stores and manages UI-related data in a lifecycle conscious way!
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - A lifecycle-aware data holder with the observer pattern. It's like a superpower!
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) - A concurrency design pattern that simplifies async code execution on Android. It's like a secret weapon!
- [Retrofit](https://square.github.io/retrofit) - The superhero REST Client for Java and Android by Square inc under Apache 2.0 license. It's a simple network library used for network transactions and
  capturing JSON response from web services.
- [GSON](https://github.com/square/gson) - The superhero JSON Parser that understands Kotlin non-nullable and default parameters. It's like a genius!
- [Flow](https://developer.android.com/kotlin/flow) - The superhero type in Coroutines that can emit multiple values sequentially. It's like a superpower for async code execution!
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - A dependency injection library for Android that reduces the boilerplate of manual dependency injection. It's like a
  magic wand!
- [Navigation Components](https://developer.android.com/guide/navigation/navigation-getting-started) - The superhero that helps you implement navigation from simple button clicks to more complex
  patterns. It's like a GPS for your app!
- [Logging Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - The superhero interceptor that logs HTTP request and response data. It's like a CCTV for
  network transactions!
- [OkHttp3 🌐](https://github.com/square/okhttp) - Looking for a reliable and efficient HTTP client for your app? Look no further than OkHttp3! This battle-tested library is used by some of the largest
  apps out there, and for good reason: it's fast, reliable, and easy to use.
- [Coil 🌀](https://github.com/coil-kt/coil) - Tired of dealing with slow and clunky image loading libraries? Say goodbye to your troubles and hello to Coil! This sleek and powerful library makes
  loading images a breeze, with an intuitive API and impressive performance.
- [Room 🏠](https://developer.android.com/training/data-storage/room) - Need a database solution that just works? Room has got you covered! This powerful library makes it easy to store and retrieve
  data from a SQLite database, with an intuitive API and robust feature set. Whether you're building a simple to-do list app or a complex CRM system, Room has everything you need.
- [Material Compose 💄](https://github.com/material-components/material-components-android-compose) - Want to make your app look and feel like a
  million bucks? Material Compose is the answer! This
  library provides a range of customizable components and widgets that will make your app look sleek and polished, with an intuitive API that makes it
  easy to get started. Whether you're building a
  small app or a massive enterprise system, Material Compose has everything you need to take your UI to the next level.

## Demo!

Check out this cool GIF of the app in action! 🎬

![image](https://github.com/user-attachments/assets/45191362-a6d3-4e82-865c-cca4a4193280)

## TODO

- [ ] Setup modularization.
- [x] Set up a local database with Room.
- [x] Set up remote data with retrofit.
- [x] Implement the repository pattern.
- [ ] Implement the use-case pattern.
- [x] Set up dependency injection with Hilt.
- [x] Migrate to Compose Material 3.
- [ ] Update the home UI to an adaptive layout (bottom navigation, navigation rail, navigation drawer).
- [ ] Implement unit testing.
- [ ] Implement UI/Instrumented Testing.

## Future

- [ ] Plan migration from Retrofit to Ktor.

## Setup Requirements

- An Android device or emulator 📱
- Android Studio 💻

## Getting Started

Ready to try it out yourself? Here's what to do:

1. Clone this project 🐑
2. Import the project into Android Studio 🚀
3. Connect your Android device via USB or start your emulator 🌐
4. Once the project has finished setting up, click the "run" button 🏃‍♂️

## Support

- Did you find this project useful? Show some love by clicking the ⭐️ button in the upper right corner! ❤️
- Notice anything else missing? File an issue 🚨
- Want to contribute? Whether it's fixing typos in docs or reviewing code, we welcome all contributions! 🤝

Thanks to https://github.com/philipplackner for initial project setup.