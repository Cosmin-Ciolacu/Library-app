package com.firstapplication.myfristapplicationandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.firstapplication.myfristapplicationandroid.utils.extensions.logErrorMessage

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

    }


    override fun onDestroy() {
        super.onDestroy()
        "onDestroySecondActivity".logErrorMessage()
    }


}