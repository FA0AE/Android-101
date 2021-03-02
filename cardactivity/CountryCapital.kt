package mx.faae.cardactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_country_capital.*

class CountryCapital : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_capital)

        val country = intent.getIntExtra("COUNTRY_INDEX", -1)
        val countriesArr = arrayListOf("Mexico", "Uruguay", "Spain", "Chile", "Argentina")
        val capitalArr = arrayListOf("CDMX", "Montevideo", "Madird", "Santiago", "Buenos Aires")

        countryView.setText(countriesArr[country])
        capitalView.setText(capitalArr[country])


    }
}