package com.test.miprimeraapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class FirstActivity : AppCompatActivity() {

    val TAG = FirstActivity::class.java.canonicalName

    private val PREFERENCE1 = "PREFERENCIA_1"
    private val PREFERENCE2 = "PREFERENCIA_2"
    private val PREFERENCE3 = "PREFERENCIA_3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG, "Hola estoy en onCreate")

        val sharedPreferences = application.getSharedPreferences("GENERAL", Context.MODE_PRIVATE)

        if (sharedPreferences.contains(PREFERENCE1)) preferencia1.setText(
            sharedPreferences.getString(
                PREFERENCE1,""
            )
        )

        if (sharedPreferences.contains(PREFERENCE2)) preferencia2.isChecked =
            sharedPreferences.getBoolean(PREFERENCE2, false)

        if (sharedPreferences.contains(PREFERENCE3)) preferencia3.isChecked =
            sharedPreferences.getBoolean(PREFERENCE3, false)

        guardarButton.setOnClickListener {
            sharedPreferences.edit().putString(PREFERENCE1, preferencia1.text.toString())
                .apply()
            sharedPreferences.edit().putBoolean(PREFERENCE2, preferencia2.isChecked).apply()
            sharedPreferences.edit().putBoolean(PREFERENCE3, preferencia3.isChecked).apply()
        }

        eliminarButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
        }
    }


    override fun onStart() {
        super.onStart()
        Log.e(TAG, "Hola estoy en onStart")
    }


    //usuari pot veure l activity!
    override fun onResume() {
        super.onResume()
        Log.e(TAG, "Hola estoy en onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "Hola estoy en onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "Hola estoy en onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "Hola estoy en onDestroy")
    }
}
