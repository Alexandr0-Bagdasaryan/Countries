package com.example.countries

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.countries.data.County
import com.example.countries.db.MyDbManager


class MainActivityAdd : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    private lateinit var CountryName_Add: TextView
    private lateinit var CountryPopulation_Add: TextView
    private lateinit var CountryDescription_Add: TextView
    private lateinit var btnAdd: Button
    private lateinit var btnOtmena: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_add)
        CountryName_Add=findViewById(R.id.etNameAdd)
        CountryPopulation_Add=findViewById(R.id.etPopulationAdd)
        CountryDescription_Add=findViewById(R.id.etDescriptionAdd)
        btnAdd=findViewById(R.id.btnADD)
        btnOtmena=findViewById(R.id.btnOTMENA)

        btnAdd.setOnClickListener {
           if(CountryPopulation_Add.text.isEmpty()||CountryName_Add.text.isEmpty()||CountryDescription_Add.text.isEmpty()){
               if(CountryPopulation_Add.text.isEmpty()){
                   CountryPopulation_Add.error = "Поле должно быть заполнено"
               }
               if(CountryName_Add.text.isEmpty()){
                   CountryName_Add.error = "Поле должно быть заполнено"
               }
               if(CountryDescription_Add.text.isEmpty()){
                   CountryDescription_Add.error = "Поле должно быть заполнено"
               }
           }
            else{
            myDbManager.openDb()
            myDbManager.insertToDb(CountryName_Add.text.toString(),CountryPopulation_Add.text.toString(),CountryDescription_Add.text.toString())
            finish()}

//            var add_name=CountryName_Add.text.toString()
//            var add_population=CountryPopulation_Add.text.toString().toInt()
//            var add_description=CountryDescription_Add.text.toString()
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("name",add_name)
//            intent.putExtra("description",add_description)
//            intent.putExtra("population",add_population)
//            viewModel.addCountries( CountryName_Add.text.toString(),
//                CountryPopulation_Add.text.toString(),
//                CountryPopulation_Add.text.toString().toInt(),)
          //  viewModel.addCountries(CountryName_Add.text.toString(), CountryDescription_Add.text.toString(), CountryPopulation_Add.text.toString().toInt())
        }

        btnOtmena.setOnClickListener {
            finish()
        }

    }


}