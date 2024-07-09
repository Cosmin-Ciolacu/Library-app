package com.firstapplication.myfristapplicationandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firstapplication.myfristapplicationandroid.R

class RegisterFragment: Fragment() {


    //Stare importanta "onCreateView" - stare care ii spune ce layout sa foloseasca
    override fun onCreateView(
        //Cu ajutorul clasei LayoutInflater
        inflater: LayoutInflater,
        //container din activitate in care sunt fragmentele si se incarca navigation
        container: ViewGroup?,
        savedInstanceState: Bundle?
        //Generare view si returnare cu Kotlin
    ): View? = inflater.inflate(R.layout.fragment_register, container, false)

}