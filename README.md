# Hipstore
_Android object persistence library. Lightweight, useful and hipster._

[![Release](https://jitpack.io/v/samiuelson/Hipstore.svg?style=flat-square)](https://jitpack.io/#samiuelson/Hipstore)

###Specs
* No SQL
* Using SharedPreferences and Gson under the hood
* Serializing objects to json format
* Compatible with Kotlin & Java Android projects
* Easy to implement

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
	        compile 'com.github.samiuelson:Hipstore:+'
	}
