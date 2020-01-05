package utils

import clasificadores.ClasificadorSupervisado
import models.Pattern
import utils.Reader.Companion.data
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.reflect.KClass
import kotlin.system.measureTimeMillis

class Evaluador (private val clazz: KClass<out Any>,
                 private val args: Array<Any>? = null,
                 private val debug : Boolean = false) {
    var trainTime : Long = 0
    var classificationTime : Long = 0
    lateinit var bestResult: Pair<Float, Array<Boolean>>

    init {
        if(debug){
            println("---------- ${clazz.simpleName} ---------------")
        }
        Reader.readFile()
        val dataSize = data.first().vector.size
        for (x in 1..(2.0).pow(dataSize).toInt()){
            val comb = intToBoolArray(x, dataSize)
            Reader.readFile(comb)

            if(debug){
                println(comb.joinToString(" - "))
            }

            val clasificadorSupervisado = getInstance()

            if(clasificadorSupervisado != null){
                val time = measureTimeMillis {
                    trainTime = measureTimeMillis {
                        clasificadorSupervisado.train(data)
                    }
                    classificationTime = measureTimeMillis {
                        clasificadorSupervisado.classify(data)
                    }

                    if(debug){
                        println("Eficacia total ${clasificadorSupervisado.resultAnalysis.getEffectiveness()*100}%")
                        println(clasificadorSupervisado.resultAnalysis)
                        println()

                        println("Tiempo de entrenamiento: $trainTime milisegundos.")
                        println("Tiempo de clasificacion: $classificationTime milisegundos.")
                    }
                }

                if (debug){
                    println("Tiempo de ejecucion: $time milisegundos.\n")
                }

                if(!::bestResult.isInitialized){
                    bestResult = Pair(clasificadorSupervisado.resultAnalysis.getEffectiveness(), comb)
                }else{
                    if(clasificadorSupervisado.resultAnalysis.getEffectiveness() > bestResult.first){
                        bestResult = Pair(clasificadorSupervisado.resultAnalysis.getEffectiveness(), comb)
                    }
                }
            }
        }
    }

    private fun getInstance() : ClasificadorSupervisado?{
        var instance : ClasificadorSupervisado? = null

        for(c in clazz.constructors){
            try {
                instance = if(args == null){
                    c.call() as ClasificadorSupervisado
                }else{
                    c.call(args = *args) as ClasificadorSupervisado
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }

        return instance
    }

    private fun intToBoolArray(num : Int, size: Int) : Array<Boolean>{
        return Array(size){
            ((num and ( 1 shl it )) shr it) == 1
        }
    }
}