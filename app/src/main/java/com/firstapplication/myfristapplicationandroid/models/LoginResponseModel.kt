package com.firstapplication.myfristapplicationandroid.models

data class LoginResponseModel(
    var token: String? = null,
    var invalidCredentials: Boolean = false
)