package clasificadores

class ResultAnalysis (private val patterns: Array<Pattern>) {
    private val matrix : Array<Array<Int>>
    private val params = getParams(patterns)
    init {
        matrix = Array(params.size){Array(params.size){ 0 }}

        for (p in patterns){
            matrix[params.indexOf(p.clase)][params.indexOf(p.claseResultante)]++
        }
    }


    fun getEffectiveness() : Float{
        if(patterns.isEmpty()) return 0f

        var correct = 0
        for (i in params.indices){
            correct += matrix[i][i]
        }

        return correct/patterns.size.toFloat()
    }


    fun getStringTable() : String{
        var string = ""

        for ((i, arr) in matrix.withIndex()){
            string += "${params[i]}->".padStart(15, ' ')
            for (value in arr){
                string += "$value "
            }
            string += "\n"
        }

        return string
    }
}