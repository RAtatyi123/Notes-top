package com.mp.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_menu.*
import kotlin.system.exitProcess

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val button_start: Button = findViewById(R.id.button_start)
        val button_exit: Button = findViewById(R.id.button_start2)
        val button_inf: Button = findViewById(R.id.button_start3)


        button_start.setOnClickListener {

            val intent = Intent(this@MenuActivity, MainActivity::class.java)
            startActivity(intent)


        }
        button_exit.setOnClickListener {

            moveTaskToBack(true);
            exitProcess(-1)
            finishAffinity()


        }
        button_inf.setOnClickListener {


            val intet1 = Intent(this@MenuActivity, AboutActivity3::class.java)
            startActivity(intet1)


        }





    }
}