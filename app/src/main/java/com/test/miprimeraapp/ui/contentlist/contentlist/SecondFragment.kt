package com.test.miprimeraapp.ui.contentlist.contentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.test.miprimeraapp.R
import kotlinx.android.synthetic.main.fragment_first.*

class SecondFragment : Fragment(){

    private lateinit var viewModel: SecondViewModel
    val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)
        return inflater.inflate(R.layout.fragment_second,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //aqui hacemos las operaciones sobre nuestra vista, ya que ahora está inflada


        viewModel.state.observe(this, Observer { event->

            when(event){
                is UISecondState.Loading ->{
                    loader.isVisible = true
                }
                is UISecondState.MembersResult->{
                    loader.isVisible = false

                }
            }
        })

        viewModel.onLoadMembers(args.AMOUNT)
        super.onViewCreated(view, savedInstanceState)
    }

}