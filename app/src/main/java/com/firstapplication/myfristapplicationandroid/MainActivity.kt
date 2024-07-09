package com.firstapplication.myfristapplicationandroid

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.firstapplication.myfristapplicationandroid.utils.extensions.logErrorMessage

class MainActivity : AppCompatActivity() {
    //Prima stare a activitatii (onCreate) - se face conexiunea cu layout-ul.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Activity_main e lagata la MainActivity prin metoda "setContentView"
        //Metoda care este apelata in starea de creare a aacticitatii
        //R fisier autogenerat care contine referinte catre tot din resurse (res)
        setContentView(R.layout.activity_main)

        //Loguri pentru verificarea starilor

        "onCreate".logErrorMessage()


        //Implementare utilizare buton
        //Referinta parametru catre buton
        val button = findViewById<Button>(R.id.btn_press)
        button.setOnClickListener(object : View.OnClickListener{
            //Implementare functiei onClick sa se duca pe SeccondActivity
            override fun onClick(p0: View?) {
                goToSecondActivity()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        "onStartMainActivity".logErrorMessage()
    }

    override fun onResume() {
        super.onResume()
        "onResumeMainActivity".logErrorMessage()
    }

    override fun onPause() {
        super.onPause()
        "onPauseMainActivity".logErrorMessage()
    }

    override fun onStop() {
        super.onStop()
        "onStopMainActivity".logErrorMessage()
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroyMainActivity".logErrorMessage()
    }


    //Creare metoda goToSecondActivity()
    fun goToSecondActivity(){
        //TO DO go to second activity
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        //Ca sa distrugem prima activitate apelam un finish
        finish()
    }





}