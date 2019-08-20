import clasificadores.*
import java.util.ArrayList



/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:46 para PatternRecognition2020-1
*/

fun main(){
    val data = readFile("C:/")

    val minimaDistancia = MinimaDistancia()
    minimaDistancia.train(data)
    minimaDistancia.classify(data.first())
    println(data.first())

}