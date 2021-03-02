package mx.faae.cardactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity()
{

    private val TOP_CREDIT_CODE: Int = 200

    // List
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
            val intCapital = Intent(baseContext, CountryCapital::class.java)
            intCapital.putExtra("COUNTRY_INDEX", position)
            //Start activity will be used beacuase we wont receive any value
            startActivity(intCapital)
        }
    }

    // Enables the second screen with the selected card type
    fun creditCalculation(v: View)
    {
        val index = selectedCard.selectedItemPosition
        //println("$index is the selected item. Its position: ${selectedCard.selectedItemPosition}")

        val intTopCalculation = Intent(this, TopCredit::class.java)

        // Passing the index to the second screen
        intTopCalculation.putExtra("CardTypeIndex", index)
        //startActivity(intTopCalculation)    //Shows second screen

        // In order to receive a value...
        startActivityForResult(intTopCalculation, TOP_CREDIT_CODE)
        // It returns within a special function
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TOP_CREDIT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val creditTop = data?.getIntExtra("CALCULATED_TOP_CREDIT", -1)
                returnProcessing(creditTop)
            }
        }
    }

    private fun returnProcessing(creditTop: Int?)
    {
        if (creditTop != null) {
            showCredit.setText(getString(R.string.credit, creditTop))
            //editTextNumber.String(R.string.credit, creditTop)
        }

    }
}