package com.firstapplication.myfristapplicationandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.firstapplication.myfristapplicationandroid.R
import com.firstapplication.myfristapplicationandroid.helpers.SharedPrefManager
import com.firstapplication.myfristapplicationandroid.models.RegisterResponseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameTextField = view.findViewById<EditText>(R.id.et_name)
        val emailTextField = view.findViewById<EditText>(R.id.et_email)
        val passwordTextField = view.findViewById<EditText>(R.id.et_password)

        val registerButton = view.findViewById<Button>(R.id.btn_register)
        val loginButton = view.findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener { goToLogin() }
        registerButton.setOnClickListener() {
            val name = nameTextField.text.toString()
            val email = emailTextField.text.toString()
            val password = passwordTextField.text.toString()
            registerUser(name, email, password)
        }
    }

    private fun goToLogin() {
        val action = RegisterFormFragmentDirections.actionRegisterFormFragmentToLoginForm()
        findNavController().navigate(action)
    }

    private fun registerUser(name: String, email: String, password: String) {
       if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
           AlertDialog.Builder(requireContext())
               .setTitle("Error")
               .setMessage("Please fill in all the fields")
               .setPositiveButton("OK", null)
               .show()

           return
        }
        callRegisterApi(name, email, password)
    }

    private fun callRegisterApi(name: String, email: String, password: String) {
        val url = "http://library-app-api.primera.agency/api/auth/register"
        val requestQueue = Volley.newRequestQueue(requireContext())

        val parameters = HashMap<String, String>()
        parameters["name"] = name
        parameters["email"] = email
        parameters["password"] = password

        val jsonObject = JSONObject(parameters as Map<*, *>)

        val registerRequest = object: StringRequest(
            Method.POST, url,
            { response ->
                val gson = Gson()
                val type = object : TypeToken<RegisterResponseModel>() {}.type

                val registerResponse = gson.fromJson<RegisterResponseModel>(response.toString(), type)

                val token = registerResponse.token
                AlertDialog.Builder(requireContext())
                    .setTitle("Success")
                    .setMessage("Registration successful")
                    .setPositiveButton("OK") { _, _ ->
                        saveTokenAndGoToBooks(token)
                    }
                    .show()
            },
            { error ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("An error occurred")
                    .setPositiveButton("OK", null)
                    .show()
            }

        ) {
            override fun getBodyContentType(): String {
                return "application/json"
            }

            override fun getBody(): ByteArray {
                return jsonObject.toString().toByteArray()
            }
        }

        requestQueue.add(registerRequest)
    }

    private fun saveTokenAndGoToBooks(token: String) {
        val sharedPrefManager = SharedPrefManager(requireContext())
        sharedPrefManager.saveToken(token)

        val action = RegisterFormFragmentDirections.actionRegisterFormFragmentToProductsFragment()
        findNavController().navigate(action)
    }
}