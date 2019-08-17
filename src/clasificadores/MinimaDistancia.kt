package clasificadores

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 16:05 para PatternRecognition2020-1
*/
class MinimaDistancia : ClasificadorSupervisado {
    lateinit var representativePattern : Array<RepresentativePattern>
    override fun train(patterns: ArrayList<Pattern>) {
        representativePattern = Array(patterns.size){
            RepresentativePattern(patterns[it])
        }
    }

    override fun classify() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}