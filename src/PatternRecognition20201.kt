import clasificadores.k_neighbours.KNeighbours
import clasificadores.minima_distancia.MinimaDistancia
import utils.Reader.Companion.getFile
import models.ImageSet
import utils.Evaluador
import kotlin.system.measureTimeMillis


/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:46 para PatternRecognition2020-1
*/

fun main(){
    println(measureTimeMillis {
        println(Evaluador(MinimaDistancia::class).bestResult)
    })
    println(measureTimeMillis {
        println(Evaluador(KNeighbours::class, arrayOf(3)).bestResult)
    })
}