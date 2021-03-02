package mx.faae.cardactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_top_credit.*

class TopCredit : AppCompatActivity()
{

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_credit)

        index = intent.getIntExtra("CardTypeIndex", 0)

    }

    fun goBack(v: View)
    {
        // Top credit operation
        var top: Double = 0.0
        val salary = salaryUser.text.toString().toInt()
        when (index ){
            1 -> top = salary * 2.5
            2 -> top = salary * 5.2
        }
        val intReturn = Intent()
        // Value to be received in the first screen
        intReturn.putExtra("CALCULATED_TOP_CREDIT", top.toInt())
        setResult(Activity.RESULT_OK, intReturn)

        // Close an activity in code (the same as the back button in physical devices)
        finish()
    }
}