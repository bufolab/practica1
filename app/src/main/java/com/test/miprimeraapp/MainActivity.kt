package com.test.miprimeraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    val TAG =  MainActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG,"Hola estoy en onCreate")
    }

    
    override fun onStart() {
        super.onStart()
        Log.e(TAG,"Hola estoy en onStart")
    }


    //usuari pot veure l activity!
    override fun onResume() {
        super.onResume()
        Log.e(TAG,"Hola estoy en onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG,"Hola estoy en onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG,"Hola estoy en onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"Hola estoy en onDestroy")
    }
}
