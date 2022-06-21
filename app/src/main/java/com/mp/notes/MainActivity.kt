package com.mp.notes

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mp.notes.database.DatabaseHelper
import com.mp.notes.model.note
import com.mp.notes.sharedPref.SharedPrefHandler
import com.mp.notesapp.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notes_add.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var listNotes = ArrayList<note>()
    val context = this
    var adapter = NoteAdapter(listNotes,context)
    var defaultLayout = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        



        val button_inf: TextView = findViewById(R.id.button_inf)

        button_inf.setOnClickListener {
            val intent = Intent(this@MainActivity, MenuActivity::class.java)
            startActivity(intent)
        }

        var sharedPref = SharedPrefHandler(context)
        var DatabaseHelper = DatabaseHelper(this)
        val cursor = DatabaseHelper.getNotes()

        println("INSIDE MAIN ACTIVITY")
        println(cursor.count)

        if(cursor.count != 0){
            while (cursor.moveToNext()) {
                listNotes.add(note(cursor.getString(1), cursor.getString(2)))
            }
            recycler_view.adapter = adapter

            var pdfDocument = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                PdfDocument()
            } else {
                TODO("VERSION.SDK_INT < KITKAT")
            }
            if(sharedPref.getLayout() == "Linear"){
                recycler_view.layoutManager = LinearLayoutManager(this)
            }
            else{
                recycler_view.layoutManager = GridLayoutManager(this, 2)
            }
        }
        else{
            Toast.makeText(this, "Add Notes", Toast.LENGTH_LONG).show()
        }



        LockBtnSetting.setOnClickListener{

            var intent = Intent(this, AddNotes::class.java)
            intent.putExtra("edit", false)
            intent.putExtra("Name", "")
            intent.putExtra("Description", "")
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        var DatabaseHelper = DatabaseHelper(this)
        val cursor = DatabaseHelper.getNotes()
        var sharedPref = SharedPrefHandler(context)

        listNotes.clear()

        while (cursor.moveToNext()) {
            listNotes.add(note(cursor.getString(1), cursor.getString(2)))
        }

        recycler_view.adapter = adapter
        if(sharedPref.getLayout() == "Linear"){
            recycler_view.layoutManager = LinearLayoutManager(this)
        }
        else{
            recycler_view.layoutManager = GridLayoutManager(this, 2)
        }
        recycler_view.setHasFixedSize(true)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var sharedPref = SharedPrefHandler(context)
        menuInflater.inflate(R.menu.main_menu, menu)




        if(menu != null && sharedPref.getLayout() == "Linear"){
            menu.getItem(0).setTitle("Grid Layout")
        }
        else if(menu != null && sharedPref.getLayout() == "Grid"){
            menu.getItem(0).setTitle("Linear Layout")
        }


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPref = SharedPrefHandler(context)
        when (item.itemId) {
            R.id.layout -> {
                if(sharedPref.getLayout() == "Linear"){
                    recycler_view.layoutManager = GridLayoutManager(this,2)
                    item.setTitle("Linear Layout")
                    sharedPref.setLayout("Grid")
                    defaultLayout = 1
                }
                else{
                    recycler_view.layoutManager = LinearLayoutManager(this)
                    item.setTitle("Grid Layout")
                    sharedPref.setLayout("Linear")
                    defaultLayout = 0
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
