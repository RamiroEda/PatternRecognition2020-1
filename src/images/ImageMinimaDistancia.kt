package images

import clasificadores.*

class ImageMinimaDistancia  : ClasificadorSupervisado {
    val representativePattern = ArrayList<ImageRepresentativePattern>()
    lateinit var resultAnalysis: ResultAnalysis

    override fun classify(pattern: Pattern) {
        var mDist = Double.POSITIVE_INFINITY
        var iDist = -1

        for((i,p) in representativePattern.withIndex()){
            val distance = euclidianDistanceOfImage(p, pattern as Image)

            if(distance < mDist){
                mDist = distance
                iDist = i
            }
        }

        pattern.claseResultante = representativePattern[iDist].clase
    }

    override fun classify(patterns: Array<Pattern>) {
        for(p in patterns) classify(p)
        resultAnalysis = ResultAnalysis(patterns)
    }

    override fun train(patterns: Array<Pattern>) {
        representativePattern.add(ImageRepresentativePattern(patterns.first() as Image))

        for(p in patterns){
            val pos = representativePattern.indexOf(p)

            if(pos == -1){
                representativePattern.add(ImageRepresentativePattern(p as Image))
            }else{
                representativePattern[pos].add(p as Image)
            }
        }

        for (p in representativePattern){
            p.update()
        }
    }
}