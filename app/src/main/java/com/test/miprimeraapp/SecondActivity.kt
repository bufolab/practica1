package com.test.miprimeraapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {


    companion object {
        val USER_NAME_ARG = "USER_NAME"
    }

    var userName: String? = null
    var myList = listOf(
        MemberModel(1,"Maria", "Gomez"),
        MemberModel(2,"Joan", "Sala"),
        MemberModel(3,"Teresa", "Sala Gomez"),
        MemberModel(4,"Ramon", "Bou Marti"),
        MemberModel(5,"Pere", "Bou Sala"),
        MemberModel(6,"Laura", "Bou Sala")
    )

   lateinit var memberEfficientAdapter:MemberEfficientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        (supportActionBar?.setDisplayHomeAsUpEnabled(true))

        retrieveArguments(intent)
        if (userName != null) {
            userNameView.text = getString(R.string.welcome_name, userName)
        }

        Glide.with(this)
            .load("https://picsum.photos/id/204/200/200")
            .into(logoImage)


         memberEfficientAdapter = MemberEfficientAdapter{ idItemToRemove->

             //Es buena practica que los datos que maneja el adapter
             //sean inmutables. Por eso es mejor copiar la lista y luego asignarla
             //En este caso los MemberModel son inmutables, sino tambien lo copiariamos
             var newList = myList.filter { it.id != idItemToRemove }
             memberEfficientAdapter.data =newList
             myList = newList
         }

        familyRecylerView.adapter =memberEfficientAdapter
        familyRecylerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        memberEfficientAdapter.data =myList
    }
    private fun retrieveArguments(intent: Intent) {
        userName = intent.getStringExtra(USER_NAME_ARG)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
