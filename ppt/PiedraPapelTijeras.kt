package mx.faae.ppt

import kotlin.random.Random

// Application model (LOGIC)
class PiedraPapelTijeras()
{
    var puntosJugador: Int = 0
        private set     // Variable cannot be change due to limited access granted by set
    var puntosComputadora: Int = 0
        private set
    val PUNTAJE_GANA = 5

    fun reset()
    {
        puntosComputadora = 0
        puntosJugador = 0
    }

    fun jugar(elemento_jugador: Elemento, elemento_computadora: Elemento): Resultado
    {
        when (elemento_jugador)             // Switch case in kotlin
        {
            elemento_computadora -> {       // ...case
                return Resultado.EMPATE
            }

            Elemento.PIEDRA -> {
                if (elemento_computadora == Elemento.TIJERAS) {
                    puntosJugador++
                    return Resultado.JUGADOR
                }
            }

            Elemento.PAPEL -> {
                if (elemento_computadora == Elemento.PIEDRA) {
                    puntosJugador++
                    return Resultado.JUGADOR
                }
            }

            Elemento.TIJERAS -> {
                if (elemento_computadora == Elemento.PAPEL) {
                    puntosJugador++
                    return Resultado.JUGADOR
                }
            }
        }

        // Computer wins if none of the cases is done
        puntosComputadora++
        return Resultado.COMPUTADORA
    }

    // The computer is able to randomly choose an element
    fun jugarAzar(): Elemento
    {
        var elementos_array = Elemento.values()
        val index = Random.nextInt(elementos_array.size)

        return elementos_array[index]
    }

    fun probarGanador():Ganador
    {
        if (puntosJugador == PUNTAJE_GANA) {
            return Ganador.JUGADOR
        } else if (puntosComputadora == PUNTAJE_GANA) {
            return Ganador.COMPUTADORA
        } else {
            return Ganador.NO_HAY_GANADOR
        }
    }
}