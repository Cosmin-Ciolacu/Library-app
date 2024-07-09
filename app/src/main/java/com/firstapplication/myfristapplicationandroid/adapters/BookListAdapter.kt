package com.firstapplication.myfristapplicationandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firstapplication.myfristapplicationandroid.R
import com.firstapplication.myfristapplicationandroid.models.BookModel
import com.firstapplication.myfristapplicationandroid.utils.extensions.logErrorMessage

class BookListAdapter(

    //In constructor adapter primeste o lista de BookModel
    private val booksList: List<BookModel>
): RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {


    //Returnam un booksList.size
    override fun getItemCount() = booksList.size

    //Metoda de creare viewHolder. Parintele este recyclie view in care se gasesste viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        "onCreateViewHolder".logErrorMessage("BookListAdapter")

        //Creare view in baza layout-ului celulei
        //Creare instanta LayoutInflater cu ajutorul contextului in care se gasete parintele si este
        // Determinat de fragmentul unde e recylceView si determinat de secondActivity
        val inflater = LayoutInflater.from(parent.context)
        //Creare view cu inflater
        val view = inflater.inflate(R.layout.book_product, parent, false)
        return BookViewHolder(view)
    }




    //Metoda pentru a da valoare view-rilor din ViewHolder
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        "onBindViewHolder; possision=$position".logErrorMessage("BookListAdapter")
        //Obtinere model din lista de carti
        booksList.getOrNull(position)?.let { holder.bind(it) }
    }


    //Creare clasa in clasam ViewHolder care se comporta ca un container
    // pentru view-rile din celula si de asignare valori pentru fiecare in parte

    inner class BookViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        val productNameTextView: TextView
        val producDescriptionTextView: TextView

        //Metoda init care se apeleaza dupa constructor - putem ajusta parametrii cu ea
        init {
            productNameTextView = view.findViewById(R.id.rv_product_name)
            producDescriptionTextView = view.findViewById(R.id.rv_description_name)
        }


        //Creare metoda care primeste un BookModel si putem atribui valori view-urilor
        fun bind(model: BookModel){
            productNameTextView.text = model.title
            producDescriptionTextView.text = model.description
        }

    }    }
