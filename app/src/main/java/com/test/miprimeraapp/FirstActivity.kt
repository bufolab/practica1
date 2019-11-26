package com.test.miprimeraapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class FirstActivity : AppCompatActivity() {

    val TAG =  FirstActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG,"Hola estoy en onCreate")

        val boton =this.findViewById<MaterialButton>(R.id.botonEntrar)
        val titulo = this.findViewById<TextView>(R.id.titulo)
        val usuario = this.findViewById<TextInputEditText>(R.id.editUsuario)
        boton.setOnClickListener {
            val nombreUsuario = usuario.text
            if(nombreUsuario?.isBlank() == true) {
                titulo.text = getString(R.string.ops_esta_vacio_escribe_algo_porfavor)
            }else {
                titulo.text = getString(R.string.hello_name,usuario.text)
            }

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(SecondActivity.USER_NAME_ARG, usuario.text.toString())
            }
            startActivity(intent)
        }
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
