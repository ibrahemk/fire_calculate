package com.example.fire_calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fire_calculate.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance(), "home").commit()
    }
}