package com.firstapplication.myfristapplicationandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firstapplication.myfristapplicationandroid.R

class LoginFragment: Fragment() {


    //Stare importanta "onCreateView" - stare care ii spune ce layout sa foloseasca
    override fun onCreateView(
        //Cu ajutorul clasei LayoutInflater
        inflater: LayoutInflater,
        //container din activitate in care sunt fragmentele si se incarca navigation
        container: ViewGroup?,
        savedInstanceState: Bundle?
        //Generare view si returnare cu ajutorul layout
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    //Starea in care intra fragmentul dupa un onCreateView si primeste View generat mai sus ca param
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Cautare buton dupa ID in view-ul generat

        val button = view.findViewById<Button>(R.id.btn_register)
        button.setOnClickListener { goToRegister() }

        view.findViewById<Button>(R.id.btn_login).setOnClickListener{
            goToLoginForm()
        }


    }

    private fun goToRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFormFragment()
        findNavController().navigate(action)
    }

    private fun goToLoginForm() {
        val action = LoginFragmentDirections.actionLoginFragmentToLoginForm()
        findNavController().navigate(action)
    }

//    private fun goToProducts(){
//        val action = LoginFragmentDirections.actionLoginFragmentToProductsFragment()
//    findNavController().navigate(action)
//    }

    }

