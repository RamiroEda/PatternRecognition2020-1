package clasificadores

import java.lang.Exception
import kotlin.math.pow
import kotlin.math.sqrt

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 15:12 para PatternRecognition2020-1
*/
fun euclidianDistanceOf(pattern1: Pattern, pattern2: Pattern) : Double{
    if(pattern1.vector.size != pattern2.vector.size) throw Exception("El tamaño de los vectores es diferente")

    var sum = 0.0

    pattern1.vector.forEachIndexed { index, value ->
        sum += (pattern2.vector[index]-value).pow(2)
    }

    return sqrt(sum)
}