package images

import clasificadores.Pattern
import clasificadores.RGB
import java.lang.Exception

class ImageRepresentativePattern(pattern: Image) : Pattern(pattern.vectorColor.size) {
    var elements = 0
    val vectorColor = ArrayList<RGB>()

    init {
        add(pattern)
        this.clase = pattern.clase
    }

    fun add(pattern: Image){
        if(pattern.vectorColor.size != this.vector.size) throw Exception("El tamaño de los vectores es diferente")
        elements++

        if (vectorColor.isEmpty()){
            vectorColor.addAll(pattern.vectorColor.map {
                RGB(it.red, it.green, it.blue)
            })
        }else{
            pattern.vectorColor.forEachIndexed { index, value ->
                this.vectorColor[index].red += value.red
                this.vectorColor[index].green += value.green
                this.vectorColor[index].blue += value.blue
            }
        }
    }

    fun update(){
        this.vectorColor.forEachIndexed { _, value ->
            value.red /= elements
            value.green /= elements
            value.blue /= elements
        }
        elements = 0
    }
}