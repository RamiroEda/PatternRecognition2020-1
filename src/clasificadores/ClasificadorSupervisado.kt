package clasificadores

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 16:03 para PatternRecognition2020-1
*/
interface ClasificadorSupervisado {
    fun train(patterns : ArrayList<Pattern>)
    fun classify(pattern: Pattern)
    fun classify(patterns: ArrayList<Pattern>)
}