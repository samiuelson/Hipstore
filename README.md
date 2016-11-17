# Hipstore
_Android objects persisting library. Natural, NoSQL and hipster._

[![Release](https://jitpack.io/v/samiuelson/Hipstore.svg?style=flat-square)](https://jitpack.io/#samiuelson/Hipstore)

###Specs
* NoSQL
* Natural & extremely easy to implement
* Serializing objects to json
* Thread-safe
* Tested
* Using SharedPreferences and Gson under the hood
* Compatible with Kotlin & Java Android projects

### Intro
Don't waste time and resources implementing SQL db in mobile app.
In modern world often all the content is stored on a server side.
Whenever your mobile client just needs to fetch and cache the data from server and persist user preferences settings Hipstore is for you.

### Keep in mind
Hipstore storage is not encrypted and you should not store any sensitive data in it. Hipstore is also not suitable for storing very large amounts of data as all the objects are loaded into memory when deserializing them from SharedPreferences file. It's just a wrapper that allows to save non-primitive objects in SharedPreferences. 

###Gradle config
To get a Hipstore project into your build:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.samiuelson:Hipstore:v1.0.1'
	}

### Usage example (Kotlin lang)
Declare entities model classes:
```kotlin
class Person(val name: String, val age: Int, val animals: List<Animal>)
class Animal(val name: String)
class SecretToken(val token: String)
```
Access `EntityStorage<T>` or `EntitiesSotage<T>` instance by passing  `SharedPreferences` instance and java `class` in constructor:
```kotlin
val storage: EntitiesStorage<Person> = EntitiesStorage<Person>(prefs, Person::class.java) // for multiple instances storage
```
or
```kotlin
val storage: EntityStorage<SecretToken> = EntityStorage<SecretToken>(prefs, SecretToken::class.java) // for single instance object storage
```
Classes above allow to perform CRUD operations.
Once you call `EntitiesStorage.add(object: Type)` or `EntityStorage.put(object: Type)` object is serialized and stored immediately.
All methods are thread-safe.
