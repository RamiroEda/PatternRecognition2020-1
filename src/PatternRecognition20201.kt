import clasificadores.Cap
import clasificadores.LernMatrix
import clasificadores.k_neighbours.KNeighbours
import clasificadores.kmeans.KMeans
import clasificadores.minima_distancia.MinimaDistancia
import models.Image
import models.Pattern
import utils.Reader.Companion.getFile
import utils.Evaluador
import utils.Graficador2D
import utils.ImageSet
import utils.Reader.Companion.data
import utils.Reader.Companion.readFile
import kotlin.system.measureTimeMillis


/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:46 para PatternRecognition2020-1
*/

fun main(){
    val data = arrayOf(
        Pattern(doubleArrayOf(1.0, 0.0, 1.0, 0.0, 1.0), "X1"),
        Pattern(doubleArrayOf(1.0, 1.0, 0.0, 0.0, 1.0), "X2"),
        Pattern(doubleArrayOf(1.0, 0.0, 1.0, 1.0, 0.0), "X3"),
        Pattern(doubleArrayOf(1.0, 0.0, 1.0, 0.0, 1.0), "X1"),
        Pattern(doubleArrayOf(1.0, 1.0, 0.0, 0.0, 1.0), "X2"),
        Pattern(doubleArrayOf(1.0, 0.0, 1.0, 1.0, 0.0), "X3"),
        Pattern(doubleArrayOf(1.0, 0.0, 1.0, 0.0, 1.0), "X1"),
        Pattern(doubleArrayOf(1.0, 1.0, 0.0, 0.0, 1.0), "X2"),
        Pattern(doubleArrayOf(1.0, 0.0, 1.0, 1.0, 0.0), "X3"),
        Pattern(doubleArrayOf(1.0, 0.0, 1.0, 0.0, 1.0), "X2"),
        Pattern(doubleArrayOf(1.0, 1.0, 0.0, 0.0, 1.0), "X3"),
        Pattern(doubleArrayOf(1.0, 0.0, 1.0, 1.0, 0.0), "X3")
    )

    val lernMatrix = Cap()
    lernMatrix.train(data)
    lernMatrix.ajustar()
    lernMatrix.classify(data)

    println(lernMatrix)
}