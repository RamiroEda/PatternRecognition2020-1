package models

import java.lang.Exception

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 15:07 para PatternRecognition2020-1
*/
class RepresentativePattern(pattern: Pattern) : Pattern(pattern.vector.size) {
    var elements = 0

    init {
        add(pattern)
        elements = 0
        this.clase = pattern.clase
    }

    fun add(pattern: Pattern){
        elements++
        if(pattern.vector.size != this.vector.size) throw Exception("El tamaño de los vectores es diferente")

        this.vector.forEachIndexed { index, value ->
            this.vector[index] = value.plus(pattern.vector[index])
        }
    }

    fun update(){
        this.vector.forEachIndexed { index, value ->
            this.vector[index] = if(elements > 0){
                value.div(elements)
            }else{
                value
            }
        }
        elements = 0
    }

    fun cloneRepresentative() : RepresentativePattern{
        return RepresentativePattern(this)
    }
}