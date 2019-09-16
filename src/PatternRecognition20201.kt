import clasificadores.k_neighbours.KNeighbours
import utils.Reader.Companion.getFile
import models.ImageSet
import kotlin.system.measureTimeMillis


/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:46 para PatternRecognition2020-1
*/

fun main(){
    val images = ImageSet(getFile().currentDirectory.absolutePath, 256, 256)

    val time = measureTimeMillis {
        val kNeighbours = KNeighbours(2)

        val trainTime = measureTimeMillis {
            kNeighbours.train(images.images.toTypedArray())
        }
        val classificationTime = measureTimeMillis {
            kNeighbours.classify(images.images.toTypedArray())
        }

        println("Eficacia total ${kNeighbours.resultAnalysis.getEffectiveness()*100}%")
        println(kNeighbours.resultAnalysis)
        println()

        println("Tiempo de entrenamiento: $trainTime milisegundos.")
        println("Tiempo de clasificacion: $classificationTime milisegundos.")
    }

    println("Tiempo de ejecucion: $time milisegundos.\n")
}