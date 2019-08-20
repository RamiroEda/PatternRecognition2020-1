package clasificadores

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 16:05 para PatternRecognition2020-1
*/
class MinimaDistancia : ClasificadorSupervisado {
    private val representativePattern = ArrayList<RepresentativePattern>()

    override fun classify(pattern: Pattern) {
        var mDist = Double.POSITIVE_INFINITY
        var iDist = -1

        for((i,p) in representativePattern.withIndex()){
            val distance = euclidianDistanceOf(p, pattern)

            if(distance < mDist){
                mDist = distance
                iDist = i
            }
        }

        pattern.claseResultante = representativePattern[iDist].clase
    }

    override fun classify(patterns: ArrayList<Pattern>) {
        for(p in patterns) classify(p)
    }

    override fun train(patterns: ArrayList<Pattern>) {
        representativePattern.add(RepresentativePattern(patterns.first()))

        for(p in patterns){
            val pos = representativePattern.indexOf(p)

            if(pos == -1){
                representativePattern.add(RepresentativePattern(p))
            }else{
                representativePattern[pos].add(p)
            }
        }

        for (p in representativePattern){
            p.update()
        }
    }
}