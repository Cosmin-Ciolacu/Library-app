package com.firstapplication.myfristapplicationandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.firstapplication.myfristapplicationandroid.R
import com.firstapplication.myfristapplicationandroid.helpers.SharedPrefManager
import com.firstapplication.myfristapplicationandroid.models.LoginResponseModel
import com.firstapplication.myfristapplicationandroid.utils.extensions.logErrorMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [LoginForm.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginForm : Fragment() {
    // TODO: Rename and change types of parameters

    private var email: String? = null
    private var password: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailTextField = view.findViewById<EditText>(R.id.et_email)
        val passwordTextField = view.findViewById<EditText>(R.id.et_password)
        val loginButton = view.findViewById<View>(R.id.btn_login)
        val registerButton = view.findViewById<View>(R.id.btn_register)

        registerButton.setOnClickListener { goToRegister() }
        loginButton.setOnClickListener() {
            email = emailTextField.text.toString()
            password = passwordTextField.text.toString()
            loginUser(email, password)
        }
    }

    private fun goToRegister() {
        val action = LoginFormDirections.actionLoginFormToRegisterFormFragment()
        findNavController().navigate(action)
    }

    private fun loginUser(email: String?, password: String?) {
        if (email.isNullOrBlank()) {
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage("Email is required")
                .setPositiveButton("OK", null)
                .show()
            return;
        }

        if (password.isNullOrBlank()) {
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage("Password is required")
                .setPositiveButton("OK", null)
                .show()
            return;
        }

        callLoginApi(email, password)
    }

    private fun callLoginApi(email: String?, password: String?) {
        val url = "https://wsgpbhkdot.sharedwithexpose.com/api/auth/login";
        val requestQueue = Volley.newRequestQueue(requireContext())

        val parameters = HashMap<String, String>()
        parameters["email"] = email!!
        parameters["password"] = password!!
        val jsonObject = JSONObject(parameters as Map<*, *>)

        val loginRequest = object: StringRequest(
            Method.POST, url,
            { response ->
                val gson = Gson()
                val type = object: TypeToken<LoginResponseModel>() {}.type
                val loginResponse = gson.fromJson<LoginResponseModel>(response, type)

                val token = loginResponse.token

                if (!token.isNullOrBlank()) {
                    saveTokenAndGoToBooks(token)
                }

            },
            { error ->
                error.toString().logErrorMessage()
                AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("Invalid email or password")
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

        requestQueue.add(loginRequest)
    }

    private fun saveTokenAndGoToBooks(token: String) {
        val sharedPrefManager = SharedPrefManager(requireContext())
        sharedPrefManager.saveToken(token)

        val action = LoginFormDirections.actionLoginFormToProductsFragment()
        findNavController().navigate(action)
    }
}