package com.test.miprimeraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.miprimeraapp.ui.main.FirstFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, FirstFragment.newInstance())
//                .commitNow()
//        }
    }

}
