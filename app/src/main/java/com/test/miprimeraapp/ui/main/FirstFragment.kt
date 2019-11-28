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
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)
        return inflater.inflate(R.layout.fragment_first,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        botonEntrar.setOnClickListener {
            val actionFirstFragmentToSecondFragment =
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(amountEditText.text.toString())
            view.findNavController().navigate(actionFirstFragmentToSecondFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

}
