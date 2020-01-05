package clasificadores.kmeans

import clasificadores.ClasificadorNoSupervisado
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import models.Pattern
import models.RepresentativePattern
import utils.Graficador2D
import utils.Reader
import utils.euclidianDistanceOf
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

data class Centroid(var position: RepresentativePattern, var positionOld: RepresentativePattern)

class KMeans (private val k : Int, private val graph : Boolean = false) : ClasificadorNoSupervisado {
    override fun train(patterns: Array<Pattern>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var patterns : Array<Pattern>
    val centroids = ArrayList<Centroid>()

    init {
        if(k <= 0) throw Exception("Numero de clusters incorrecto. Valor $k no cumple K > 0.")
    }

    constructor (patterns: Array<Pattern>) : this(patterns.size) {
        patterns.mapIndexedTo(this.centroids) { i, it ->
            val pattern = RepresentativePattern(it)
            pattern.clase = i.toString()
            pattern.claseResultante = "Centroide $i"

            val centroid = Centroid(pattern, pattern)

            centroid
        }
    }

    override fun classify(pattern: Pattern) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun classify(patterns: Array<Pattern>) {
        this.patterns = patterns

        if(centroids.isEmpty()){
            for(i in 0 until k){
                val randPattern = searchUniquePattern(Calendar.getInstance().timeInMillis)
                randPattern.clase = i.toString()
                randPattern.claseResultante = "Centroide $i"
                this.centroids.add(Centroid(randPattern, randPattern))
            }
        }

        if(graph){
            GlobalScope.launch {
                classificationProcessGraph()
            }
        }else{
            classificationProcess()
        }
    }

    private suspend fun classificationProcessGraph(){
        val graficador = Graficador2D(patterns)

        var it = 1
        do {
            assignChildren()
            calculateMeans()
            it++
            if(graph) delay(1000)
            graficador.newChart()
        }while (!allCentroidsUnchanged())
        println(it)
    }

    private fun classificationProcess(){
        var it = 1
        do {
            assignChildren()
            calculateMeans()
            it++
        }while (!allCentroidsUnchanged())
        println(it)
    }

    private fun searchUniquePattern(seed : Long) : RepresentativePattern{
        var repeat: Int
        var patternGen: Pattern
        do{
            patternGen = patterns.random(Random(seed))
            repeat = centroids.count {
                it.position === patternGen
            }
        }while (repeat > 0)

        return RepresentativePattern(patternGen)
    }

    private fun assignChildren(){
        for(c in centroids){
            c.positionOld = c.position.cloneRepresentative()
        }

        val centroidsZip = centroids.zip(centroids.toTypedArray()){ a, b ->
            Pair(a, Centroid(b.position.cloneRepresentative(), b.positionOld.cloneRepresentative()))
        }

        for(c in centroids){
            c.position.vector = DoubleArray(c.position.vector.size)
        }

        for(p in patterns){
            var nearestCentroid : Pair<Centroid, Double> = Pair(centroidsZip.first().first, Double.MAX_VALUE)

            for((centroid, centroidCopy) in centroidsZip){
                val dist = euclidianDistanceOf(centroidCopy.position, p)
                if (dist < nearestCentroid.second){
                    nearestCentroid = Pair(centroid, dist)
                }
            }

            nearestCentroid.first.position.add(p)
            p.claseResultante = nearestCentroid.first.position.clase
        }

        for((centroid, centroidCopy) in centroidsZip){
            if(centroid.position.vector.average() == 0.0){
                centroid.position = centroidCopy.positionOld
            }
        }
    }

    private fun calculateMeans(){
        for (c in centroids){
            c.position.update()
        }
    }

    private fun allCentroidsUnchanged() : Boolean{
        var changed = 0
        for(c in centroids){
            if (!c.position.vector.contentEquals(c.positionOld.vector)) changed++
        }

        return changed == 0
    }
}