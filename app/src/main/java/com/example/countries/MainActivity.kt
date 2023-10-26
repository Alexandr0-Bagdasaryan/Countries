package com.example.countries

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.countries.db.MyDbManager

class MainActivity : AppCompatActivity() {

    val myDbManager =MyDbManager(this)
    private lateinit var btnNext: ImageButton
    private lateinit var btnPrevious: ImageButton
    private lateinit var CountryName: TextView
    private lateinit var CountryPopulation: TextView
    private lateinit var CountryDescription: TextView
    private lateinit var Delete:Button
    private lateinit var Add:Button
    private lateinit var Change:Button
    lateinit var listNames:ArrayList<String>
    lateinit var listDescription:ArrayList<String>
    lateinit var listPopulation:ArrayList<String>
    var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        myDbManager.openDb()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNext = findViewById<ImageButton>(R.id.btnNext)
        btnPrevious=findViewById<ImageButton>(R.id.btnPrevious)
        CountryName=findViewById(R.id.tvName)
        CountryPopulation=findViewById(R.id.tvPopulation)
        CountryDescription=findViewById(R.id.tvDescription)
        Delete=findViewById(R.id.btnDelete_Add)
        Add=findViewById(R.id.btnAdd_Add)
        Change=findViewById(R.id.btnToChange)
        listNames=myDbManager.readDbData_Names()
        listDescription=myDbManager.readDbData_Description()
        listPopulation=myDbManager.readDbData_Population()
        updateData()


        btnNext.setOnClickListener {
            if(listNames.isNotEmpty()){
            currentIndex = (currentIndex + 1) % listNames.size
            updateData()
        }
        }
        btnPrevious.setOnClickListener {
            if(listNames.isNotEmpty()){
            currentIndex = (listNames.size + currentIndex - 1) % listNames.size
            updateData()
        }}
        Delete.setOnClickListener{
            if(listNames.isNotEmpty()){
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Удаление записи")
            alertDialogBuilder.setMessage("Действительно ли вы хотите удалить запись?")

            alertDialogBuilder.setPositiveButton("Да") { _, _ ->
                //viewModel.deleteCurrentCounty()
                myDbManager.deleteFromDb(CountryName.text.toString())
                currentIndex=0
                updateData()
            }

            alertDialogBuilder.setNegativeButton("Нет") { _, _ ->

            }

            val alertDialog: AlertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }}
        val addCountryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //viewModel.updateCountry
                updateData()

            }

        }

        Add.setOnClickListener {
            val intent = Intent(this, MainActivityAdd::class.java)
            addCountryLauncher.launch(intent)
        }

        Change.setOnClickListener {
            sendDataToNextActivity(CountryName.text.toString(),CountryPopulation.text.toString(),CountryDescription.text.toString())
        }

    }


     fun updateData(){
         listNames.clear()
         listDescription.clear()
         listPopulation.clear()
         listNames=myDbManager.readDbData_Names()
         listDescription=myDbManager.readDbData_Description()
         listPopulation=myDbManager.readDbData_Population()
         if(listNames.isNotEmpty()){
        val countryName = listNames[currentIndex]
        CountryName.setText(countryName)
        val countryPopulation = listPopulation[currentIndex]
        CountryPopulation.setText(countryPopulation)
        val countryDescription = listDescription[currentIndex]
        CountryDescription.setText(countryDescription)
    }
     else{
             CountryName.setText("")
             CountryPopulation.setText("")
             CountryDescription.setText("")
     }
     }


    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        updateData()
    }

    fun sendDataToNextActivity(name: String,population: String,description: String) {
        val intent = Intent(this, MainActivityChange::class.java)
        intent.putExtra("name", name)
        intent.putExtra("population", population)
        intent.putExtra("description", description)
        startActivity(intent)
    }
}