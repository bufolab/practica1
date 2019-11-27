package com.test.miprimeraapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.test.miprimeraapp.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_first,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
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
