package com.test.miprimeraapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.test.miprimeraapp.R
import kotlinx.android.synthetic.main.activity_main.*
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
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_first,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        botonEntrar.setOnClickListener {
            viewModel.onLogin(editUsuario.text.toString())
        }

        viewModel.state.observe(this, Observer { event->

            when(event){
                is UIMainState.Loading ->{
                    loader.isVisible = true
                }
                is UIMainState.UserLoginResult->{
                    loader.isVisible = false
                    Toast.makeText(requireContext(),
                        "Just for testing, is logged result: ${event.success}",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }
}
