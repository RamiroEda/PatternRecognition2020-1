package clasificadores

import models.Image
import models.Pattern
import models.ResultAnalysis
import utils.ImageSet

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 16:03 para PatternRecognition2020-1
*/
interface ClasificadorSupervisado {
    var resultAnalysis: ResultAnalysis

    fun train(patterns : Array<Pattern>)
    fun classify(pattern: Pattern)
    fun classify(patterns: Array<Pattern>)

    fun train(patterns : ImageSet)
    fun classify(pattern: Image)
    fun classify(patterns: ImageSet)
}