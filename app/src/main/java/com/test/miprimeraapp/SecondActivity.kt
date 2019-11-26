package com.test.miprimeraapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {


    companion object{
       val USER_NAME_ARG = "USER_NAME"
    }
    var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        (supportActionBar?.setDisplayHomeAsUpEnabled(true))

        retrieveArguments(intent)
        if(userName != null) {
            userNameView.text = getString(R.string.welcome_name, userName)
        }

        Glide.with(this)
            .load("https://picsum.photos/id/204/200/200")
            .into(logoImage)

        val myList = listOf("Abuela","Abuelo","Padre","Madre","Hijo","Hija")
        familyListView.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList)
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
