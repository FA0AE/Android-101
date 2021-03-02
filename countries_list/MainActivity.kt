package mx.faae.countries_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    // list declaration
    private lateinit var countriesArr: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listConfiguration()
    }

    private fun listConfiguration()
    {
        countriesArr = arrayListOf("Mexico", "Uruguay", "Spain", "Chile", "Argentina")
        countriesList.choiceMode - ListView.CHOICE_MODE_SINGLE
        val adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_activated_1, countriesArr)
        countriesList.adapter = adapter

        countriesList.setOnItemClickListener { parent, view, position, id ->
            // Intent will be used to launch a second screen
            val intCapital = Intent(baseContext, CapitalScreen::class.java)
            intCapital.putExtra("COUNTRY_INDEX", position)
            //Start activity will be used beacuase we wont receive any value
            startActivity(intCapital)
        }
    }
}