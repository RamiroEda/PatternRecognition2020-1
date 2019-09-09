import clasificadores.MinimaDistancia
import clasificadores.getFile
import images.ImageMinimaDistancia
import images.ImageSet
import images.viewRepresentativePattern


/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:46 para PatternRecognition2020-1
*/

fun main(){
    //readFile()
    val imageSet = ImageSet(getFile().currentDirectory.absolutePath, 256, 256)
    val patterns = imageSet.toPatternArray()

    val minimaDistancia = ImageMinimaDistancia()

    minimaDistancia.train(patterns)
    minimaDistancia.classify(patterns)

    viewRepresentativePattern(minimaDistancia.representativePattern, 256, 256)

    println("${(minimaDistancia.resultAnalysis.getEffectiveness()*100)}% de eficacia")
    println(minimaDistancia.resultAnalysis.getStringTable())
}