import clasificadores.k_neighbours.KNeighbours
import clasificadores.minima_distancia.ImageViewerOriginal
import clasificadores.minima_distancia.MinimaDistancia
import utils.Reader.Companion.getFile
import clasificadores.minima_distancia.images.ImageMinimaDistancia
import clasificadores.minima_distancia.images.ImageViewer
import models.Image
import models.ImageSet
import models.viewRepresentativePattern
import utils.Reader.Companion.data
import utils.Reader.Companion.readFile


/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:46 para PatternRecognition2020-1
*/

fun main(){
    val imageSet = ImageSet(getFile().currentDirectory.absolutePath, 256, 256)
    val patterns = imageSet.toPatternArray()

    val minimaDistancia = ImageMinimaDistancia()

    minimaDistancia.train(patterns)

    val elZamu = Image(getFile().selectedFile, 256, 256)

    minimaDistancia.classify(elZamu)
    println(elZamu)

    //viewRepresentativePattern(minimaDistancia.representativePattern, 256, 256)

    //println("${minimaDistancia.resultAnalysis.getEffectiveness()*100}% de eficacia total")
    //println(minimaDistancia.resultAnalysis)
}