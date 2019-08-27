import clasificadores.MinimaDistancia
import clasificadores.Reader.Companion.data
import clasificadores.Reader.Companion.readFile


/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:46 para PatternRecognition2020-1
*/

fun main(){
    readFile(arrayOf(true, false, false, true))
    val minimaDistancia = MinimaDistancia()
    minimaDistancia.train(data)
    minimaDistancia.classify(data)
    println(data)
}