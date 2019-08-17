package clasificadores

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 14:47 para PatternRecognition2020-1
*/
open class Pattern {
    var vector : Array<Double>
    var clase = "Desconocida"
    var claseResultante = "Desconocida"

    constructor(size : Int){
        vector = Array(size){
            0.0
        }
    }

    constructor(vector: Array<Double>, clase: String){
        this.vector = vector
        this.clase = clase
    }

    constructor(pattern: Pattern){
        this.clase = pattern.clase
        this.claseResultante = pattern.claseResultante
        this.vector = pattern.vector
    }
}