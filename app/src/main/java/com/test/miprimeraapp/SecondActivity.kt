package com.test.miprimeraapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {


    companion object{
       val USER_NAME_ARG = "USER_NAME"
    }
    lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        (supportActionBar?.setDisplayHomeAsUpEnabled(true))

        retrieveArguments(intent)
        userNameView.text = getString(R.string.welcome_name,userName)
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
