package clasificadores.k_neighbours

import clasificadores.ClasificadorSupervisado
import models.Image
import models.Pattern
import models.ResultAnalysis
import utils.ImageSet
import utils.euclidianDistanceOf
import utils.euclidianDistanceOfImage
import utils.getClasses

class KNeighbours (private val kNeighbours: Int) : ClasificadorSupervisado {
    private data class Pair(val clase: String, var count: Int = 0)
    lateinit var patterns: Array<Pattern>
    override lateinit var resultAnalysis: ResultAnalysis
    private lateinit var classes : Array<String>

    override fun train(patterns: Array<Pattern>) {
        this.patterns = patterns.clone()
        classes = getClasses(patterns)
    }

    override fun classify(pattern: Pattern) {
        patterns.sortBy {
            euclidianDistanceOf(pattern, it)
        }

        classification(pattern)
    }

    override fun classify(patterns: Array<Pattern>) {
        for(p in patterns){
            classify(p)
        }
        this.resultAnalysis = ResultAnalysis(this.patterns)
    }


    override fun classify(pattern: Image) {
        patterns.sortBy {
            euclidianDistanceOfImage(pattern, it as Image)
        }

        classification(pattern)
    }

    private fun classification(pattern: Pattern){
        val classesCount = classes.map {
            Pair(it)
        }.toTypedArray()

        for (p in patterns){
            if(++classesCount[classes.indexOf(p.clase)].count >= kNeighbours){
                break
            }
        }

        val resultante = classesCount.maxBy {
            it.count
        }

        if(resultante != null){
            pattern.claseResultante = resultante.clase
        }
    }

    override fun train(patterns: ImageSet) {
        val patternArray = patterns.toPatternArray()
        this.patterns = patternArray.clone()
        classes = getClasses(patternArray)
    }

    override fun classify(patterns: ImageSet) {
        for(p in patterns.images){
            classify(p)
        }
        this.resultAnalysis = ResultAnalysis(this.patterns)
    }

}