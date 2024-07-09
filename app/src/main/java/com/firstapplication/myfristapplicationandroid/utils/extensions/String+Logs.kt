package com.firstapplication.myfristapplicationandroid.utils.extensions

import android.util.Log

//Creare extensii are clasei String - functionalitate pe care nu o are
//Creare functionalitate pentru String pentru creare log cu acel string

fun String.logErrorMessage(tag: String = "AppTag"){
    Log.e(tag, this) //this e stringul care apeleaza metoda. Tag il trimitem asa ca il dam ca param
}

//fun doSomething(){
//    val myString = "bla"
//    //Creaza un log
//    myString.logErrorMessage()
//}