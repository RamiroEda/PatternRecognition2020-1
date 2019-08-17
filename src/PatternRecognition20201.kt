import clasificadores.Pattern
import clasificadores.RepresentativePattern
import clasificadores.euclidianDistanceOf

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:46 para PatternRecognition2020-1
*/

fun main(){
    val p1 = Pattern(arrayOf(11.5, 5.7, 4.8), "")
    val p2 = Pattern(arrayOf(1.0, 1.0, 1.0), "")
    val p3 = Pattern(arrayOf(12.0, 113.0, 14.0), "")

    val representativePattern = RepresentativePattern(p3)
    representativePattern.add(p1)
    representativePattern.add(p2)
    representativePattern.update()

    println(euclidianDistanceOf(p1,p2))
}