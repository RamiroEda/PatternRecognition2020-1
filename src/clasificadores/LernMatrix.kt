package clasificadores

import models.Pattern
import utils.getClasses

open class LernMatrix : ClasificadorNoSupervisado{
    private lateinit var classes : Array<String>
    private lateinit var matrix : Array<Pattern>
    private lateinit var dataset : Array<Pattern>

    companion object{
        private const val EPSILON = 1.0
    }

    open fun getClasses() = classes
    open fun getMatrix() = matrix
    open fun getDataset() = dataset

    override fun train(patterns: Array<Pattern>) {
        this.dataset = patterns
        classes = getClasses(patterns)
        matrix = Array(classes.size){
            Pattern(patterns.first().vector.size)
        }


        for(p in patterns){
            val classIndex = classes.indexOf(p.clase)
            for ((i, c) in p.vector.withIndex()){
                if(c == 0.0){
                    matrix[classIndex].vector[i] -= EPSILON
                }else if(c == 1.0){
                    matrix[classIndex].vector[i] += EPSILON
                }
            }
        }
    }

    override fun classify(pattern: Pattern) {
        val res = pattern.times(matrix)

        pattern.claseResultante = classes[res.indexOf(res.max())]
    }

    override fun classify(patterns: Array<Pattern>) {
        for(p in patterns){
            classify(p)
        }
    }

}