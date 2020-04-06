## Android MVVM Architecture Example
## Table of contents
* [General info](#general-info)
* [Architecture](#architecture)
* [Flow](#flow)
* [Technologies](#technologies)
* [Libraries](#libraries)

## General info
Android MVVM architecture with login screen, permanent token, feed list containing video and video playing single fragment

## Architecture
MVVM architecture with Dagger 2 dependency injection. Application contains 3 Activities (Splash, Authentication and Main). MainActivity contains 4 Fragments separated by features, Search, RepositoryDetails, User and OwnerDetails.

## Flow
![flow](https://i.imgur.com/DPTXw8y.png)

## Technologies
* Android
* Kotlin

## Libraries
* Dagger 2
* Gson
* Coroutines
* Retrofit
* LiveData
* DataBinding
* Glide
* Room
* Navigation Component
* Paging
* Mockito
