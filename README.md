# SampleProject-Test
## How to run tests
* Run Android simulator or connect an Android device.
* Build app:
```
./gradlew clean assembledebug --stacktrace
```
* Run UI tests:
```
./gradlew connectedAndroidTest
```
* Run Unit tests:
```
./gradlew test
```