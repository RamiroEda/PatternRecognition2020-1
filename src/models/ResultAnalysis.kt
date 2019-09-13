package models

import utils.getClasses

class ResultAnalysis (private val patterns: Array<Pattern>) {
    private val matrix : Array<IntArray>
    private val classes = getClasses(patterns)
    init {
        matrix = Array(classes.size){IntArray(classes.size)}

        for (p in patterns){
            matrix[classes.indexOf(p.clase)][classes.indexOf(p.claseResultante)]++
        }
    }


    fun getEffectiveness() : Float{
        if(patterns.isEmpty()) return 0f

        var correct = 0
        for (i in classes.indices){
            correct += matrix[i][i]
        }

        return correct/patterns.size.toFloat()
    }

    fun getEffectivnessByRow(row: Int) : Float{
        if(row >= classes.size) return -1f
        if(matrix[row].isEmpty()) return 0f

        var total = 0f
        for (n in matrix[row]){
            total += n
        }

        if (total == 0f) return 0f

        return matrix[row][row]/total
    }


    override fun toString(): String {
        var string = ""

        for ((i, arr) in matrix.withIndex()){
            string += classes[i].padEnd(15, ' ')
            for (value in arr){
                string += " "+value.toString().padEnd(4, ' ')
            }
            string += " = ${getEffectivnessByRow(i)*100}%\n"
        }

        return string
    }
}