package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.countries.db.MyDbManager

class MainActivityChange : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    private lateinit var etName:EditText
    private lateinit var etPopulation:EditText
    private lateinit var etDescription:EditText
    private lateinit var Cancel:Button
    private lateinit var Accept:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name")!!
        val population = intent.getStringExtra("population")!!
        val description = intent.getStringExtra("description")!!
        setContentView(R.layout.activity_main_change)
        etName=findViewById(R.id.etChangeName)
        etDescription=findViewById(R.id.etChangeDescription)
        etPopulation=findViewById(R.id.etChangePopulation)
        etName.setText(name)
        etPopulation.setText(population)
        etDescription.setText(description)
        Accept=findViewById(R.id.btnAccept)
        Cancel=findViewById(R.id.btnCancel)
        Accept.setOnClickListener {
            if(etName.text.isEmpty()||etDescription.text.isEmpty()||etPopulation.text.isEmpty()) {
            if (etName.text.isEmpty()) {
                etName.error = "Поле должно быть заполнено"
            }
            if (etPopulation.text.isEmpty()) {
                etPopulation.error = "Поле должно быть заполнено"
            }
            if (etDescription.text.isEmpty()) {
                etDescription.error = "Поле должно быть заполнено"
            }
        }
            else{
                myDbManager.openDb()
                myDbManager.updateInDb(name,etName.text.toString(),etPopulation.text.toString(),etDescription.text.toString())
                finish() }
        }
        Cancel.setOnClickListener { finish() }
    }
}