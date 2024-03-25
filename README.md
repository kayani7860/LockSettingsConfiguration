This repository contains an Android application developed in Kotlin, incorporating Ktor for networking, 
MVVM architecture for robust code organization, LiveData for reactive data flow, ViewBinding for efficient view manipulation, 
and Room Database for local data storage.

The API response is mapped to "LockConfig" Data class, which is then further mapped to "Parameter" data class, 
which was made Parcelable so that it could be passsed as a custom object in safe args to transfer data between fragments.

