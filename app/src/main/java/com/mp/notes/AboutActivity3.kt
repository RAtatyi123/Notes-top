package com.mp.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AboutActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        val button_back: TextView = findViewById(R.id.button_back)

        button_back.setOnClickListener {
            val intent = Intent(this@AboutActivity3, MainActivity::class.java)
            startActivity(intent)
        }



    }
}