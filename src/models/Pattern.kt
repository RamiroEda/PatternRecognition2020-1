package models

import utils.randomDouble

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:47 para PatternRecognition2020-1
*/
open class Pattern {
    var vector : DoubleArray
    var clase = "Desconocida"
    var claseResultante = "Desconocida"

    companion object{
        fun random(size: Int) = Pattern(DoubleArray(size){ randomDouble()}, "Desconocida")
    }

    constructor(size : Int){
        vector = DoubleArray(size)
    }

    constructor(vector: DoubleArray, clase: String){
        this.vector = vector.clone()
        this.clase = clase
    }

    constructor(pattern: Pattern){
        this.clase = pattern.clase
        this.claseResultante = pattern.claseResultante
        this.vector = pattern.vector.clone()
    }

    override fun equals(other: Any?): Boolean {
        if(other !is Pattern) return false

        return other.clase == this.clase
    }

    override fun hashCode(): Int {
        var result = vector.contentHashCode()
        result = 31 * result + clase.hashCode()
        result = 31 * result + claseResultante.hashCode()
        return result
    }

    override fun toString(): String {
        return "Clase=\"$clase\", Resultado=\"$claseResultante\", Vector=${vector.joinToString(", ", "[", "]")}"
    }

    fun clone() : Pattern{
        return Pattern(this)
    }

    operator fun plus(p : Pattern){
        for((i, c) in p.vector.withIndex()){
            this.vector[i] += c
        }
    }

    operator fun minus(p : Pattern){
        for((i, c) in p.vector.withIndex()){
            this.vector[i] -= c
        }
    }

    operator fun times(p : Pattern){
        for((i, c) in p.vector.withIndex()){
            this.vector[i] *= c
        }
    }

    operator fun times(p : Double){
        for(i in this.vector.indices){
            this.vector[i] *= p
        }
    }

    operator fun times(p : Array<Pattern>) : Array<Double> = Array(p.size){
        var res = 0.0
        for((i, c) in p[it].vector.withIndex()){
            res += c*this.vector[i]
        }

        return@Array res
    }

    operator fun div(p : Pattern){
        for((i, c) in p.vector.withIndex()){
            this.vector[i] /= c
        }
    }

    operator fun div(p : Double){
        for(i in this.vector.indices){
            this.vector[i] /= p
        }
    }
}