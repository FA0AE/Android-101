package mx.faae.countries_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_capital_screen.*

class CapitalScreen : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capital_screen)

        val country = intent.getIntExtra("COUNTRY_INDEX", -1)
        val countriesArr = arrayListOf("Mexico", "Uruguay", "Spain", "Chile", "Argentina")
        val capitalArr = arrayListOf("CDMX", "Montevideo", "Madird", "Santiago", "Buenos Aires")
        selectedCountry.setText(getString(R.string.country_selection, countriesArr[country]))
        countryCapital.setText(capitalArr[country])
    }

    fun goBack(v: View)
    {
        finish()
    }
}