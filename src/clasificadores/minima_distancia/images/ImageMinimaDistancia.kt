package clasificadores.minima_distancia.images

import clasificadores.*
import models.Image
import models.ImageRepresentativePattern
import models.Pattern
import models.ResultAnalysis
import utils.ImageSet
import utils.euclidianDistanceOfImage

class ImageMinimaDistancia  : ClasificadorSupervisado {
    override fun train(patterns: ImageSet) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun classify(pattern: Image) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun classify(patterns: ImageSet) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val representativePattern = ArrayList<ImageRepresentativePattern>()
    override lateinit var resultAnalysis: ResultAnalysis

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