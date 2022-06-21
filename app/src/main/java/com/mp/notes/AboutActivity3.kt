package com.mp.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class AboutActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)



        val button_back: TextView = findViewById(R.id.button_back)





        button_back.setOnClickListener {
            val intent = Intent(this@AboutActivity3, MenuActivity::class.java)
            startActivity(intent)
        }



    }
}