package clasificadores.k_neighbours

import clasificadores.ClasificadorSupervisado
import models.Pattern
import models.ResultAnalysis
import utils.euclidianDistanceOf
import utils.getClasses

class KNeighbours (private val kNeighbours: Int) : ClasificadorSupervisado {
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

        val neighbors = patterns.take(kNeighbours)

        var max = Pair(neighbors.first().clase, Int.MIN_VALUE)

        for (c in classes){
            val count = neighbors.count { it.clase == c }
            if(count > max.second){
                max = Pair(c, count)
            }
        }

        pattern.claseResultante = max.first
    }

    override fun classify(patterns: Array<Pattern>) {
        for(p in patterns){
            classify(p)
        }
        this.resultAnalysis = ResultAnalysis(this.patterns)
    }

}