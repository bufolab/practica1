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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_second.*
import androidx.recyclerview.widget.DividerItemDecoration



class SecondFragment : Fragment(){

    private lateinit var viewModel: SecondViewModel
    private lateinit var memberEfficientAdapter:MemberEfficientAdapter
    val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)
        return inflater.inflate(com.test.miprimeraapp.R.layout.fragment_second,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //aqui hacemos las operaciones sobre nuestra vista, ya que ahora está inflada

        memberEfficientAdapter = MemberEfficientAdapter{
            viewModel.deleteItem(it)
        }

        familyRecylerView.adapter =memberEfficientAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        familyRecylerView.layoutManager = linearLayoutManager

        val mDividerItemDecoration = DividerItemDecoration(
            familyRecylerView.context,
            linearLayoutManager.orientation
        )
        familyRecylerView.addItemDecoration(mDividerItemDecoration)

        viewModel.state.observe(this, Observer { event->

            when(event){
                is UISecondState.Loading ->{
                    loader.isVisible = true
                }
                is UISecondState.GetPostResult->{
                    loader.isVisible = false
                    memberEfficientAdapter.data = event.posts
                }
                is UISecondState.DeletePostResult->{

                    if(event.success){
                            viewModel.onLoadPosts(args.AMOUNT)
                    }else{
                        loader.isVisible = false
                        Toast.makeText(requireContext(),"Error borrando elemento",Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        viewModel.onLoadPosts(args.AMOUNT)
        super.onViewCreated(view, savedInstanceState)
    }

}