package com.test.miprimeraapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        botonEntrar.setOnClickListener {
            val actionFirstFragmentToSecondFragment =
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(editUsuario.text.toString())
            view.findNavController().navigate(actionFirstFragmentToSecondFragment)
        }


        super.onViewCreated(view, savedInstanceState)
    }
}