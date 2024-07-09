package com.firstapplication.myfristapplicationandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.firstapplication.myfristapplicationandroid.R
import com.firstapplication.myfristapplicationandroid.adapters.BookListAdapter
import com.firstapplication.myfristapplicationandroid.helpers.SharedPrefManager
import com.firstapplication.myfristapplicationandroid.models.BookModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BooksFragment: Fragment() {

//Metoda care face legatura cu layaout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_books, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        //Definire Layout Manager
       val sharedPrefManager = SharedPrefManager(requireContext())

        val token = sharedPrefManager.getToken()
        val url = "https://wsgpbhkdot.sharedwithexpose.com/api/books"
        val requestQueue = Volley.newRequestQueue(requireContext())

        val booksRequest = object: StringRequest(
            Method.GET,
            url,
            { response ->
                val books = Gson().fromJson<List<BookModel>>(response, object: TypeToken<List<BookModel>>() {}.type)
                buildBooksList(books)
            },
            { error ->
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Error")
                    setMessage("Error")
                    setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    show()
                }
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }

        requestQueue.add(booksRequest)

    }

    private fun buildBooksList(books: List<BookModel>) {
        val layoutManager = LinearLayoutManager(context)



        //Creare instanta adapter
        val adapter = BookListAdapter(books)


        //Cautare dupa ID
        //Cautam un view fragmentului daca exista cauta dupa id acest rv_book si se executa apply
        view?.findViewById<RecyclerView>(R.id.rv_book)?.apply {
            //Referire la rv . This este instanta obiectului care apeleaza apply
            //Nu e nevoie sa mai facem noi un paramentru (functioneaza ca un .builder)
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }
}