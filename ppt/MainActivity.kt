package mx.faae.ppt

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    // Model reference
    // -> No recommendable: val modelo: PiedraPapelTijeras = PiedraPapelTijeras()
    lateinit var modelo: PiedraPapelTijeras          // Later variable initialization (non-nullable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the model. Connection between the view and the model
        modelo = PiedraPapelTijeras()
        configureFAB()
    }

    private fun configureFAB() {
        // Add a listener and show the 'about' screen
        fab.setOnClickListener {                // it = paramaters that references the event
            // Screen change
            val intAbout = Intent(this, About::class.java)
            startActivity(intAbout)
        }
    }

    // Event registration for three buttons
    fun jugarUsuario(v: View)
    {
        val elementoComputadora: Elemento = modelo.jugarAzar()
        var elementoJugador = Elemento.PIEDRA
        if (v == boton_papel) {
            elementoJugador = Elemento.PAPEL
        } else if (v == boton_tijeras) {
            elementoJugador = Elemento.TIJERAS
        }

        val mensaje = "Player chose: $elementoJugador, Computer chose: $elementoComputadora"
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

        val resultado = modelo.jugar(elementoJugador, elementoComputadora)
        val mensaje_resultado = "Play result: $resultado"
        Toast.makeText(this, mensaje_resultado, Toast.LENGTH_SHORT).show()

        actualizarPuntaje()
        probarGanador()
    }

    private fun probarGanador() {
        val resultado = modelo.probarGanador()
        if (resultado == Ganador.NO_HAY_GANADOR) {
            return
        }

        mostrarMensaje("$resultado won the match")
    }

    private fun mostrarMensaje(mensaje_ganador: String) {
        val builder = AlertDialog.Builder(this)     // Creates the dialog square
        builder.setTitle("Winner message").setMessage(mensaje_ganador)
            .setPositiveButton("Got it!", DialogInterface.OnClickListener {
                dialog, which ->
                modelo.reset()
                actualizarPuntaje()
            })
            .setCancelable(false)
            .create()
        builder.show()
    }

    private fun actualizarPuntaje()
    {
        val puntosJugador = modelo.puntosJugador
        val puntosCoomputadora = modelo.puntosComputadora
        etPuntosJugador.setText(puntosJugador.toString())
        etPuntosComputadora.setText(puntosCoomputadora.toString())
    }
}