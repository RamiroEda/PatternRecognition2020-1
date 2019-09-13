package utils

import models.Image
import models.ImageRepresentativePattern
import models.Pattern
import java.io.File
import java.io.IOException
import javax.swing.JFileChooser
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.math.sqrt

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 15:12 para PatternRecognition2020-1
*/
data class RGB(var red : Int = 0, var green : Int = 0, var blue : Int = 0)

fun euclidianDistanceOf(pattern1: Pattern, pattern2: Pattern) : Double{
    if(pattern1.vector.size != pattern2.vector.size) throw Exception("El tamaño de los vectores es diferente")

    var sum = 0.0

    pattern1.vector.forEachIndexed { index, value ->
        sum += (pattern2.vector[index]-value).pow(2)
    }

    return sqrt(sum)
}

fun euclidianDistanceOfImage(pattern1: ImageRepresentativePattern, pattern2: Image) : Double{
    if(pattern1.vectorColor.size != pattern2.vectorColor.size) throw Exception("El tamaño de los vectores es diferente")

    var sum = 0.0

    pattern1.vectorColor.forEachIndexed { index, value ->
        sum += colorDifference(value, pattern2.vectorColor[index])
    }

    return sqrt(sum)
}

fun colorDifference(c1 : RGB, c2 : RGB) : Double = (c1.red-c2.red).toDouble().pow(2)+(c1.green-c2.green).toDouble().pow(2)+(c1.blue-c2.blue).toDouble().pow(2)

fun getClasses(patterns: Array<Pattern>) : Array<String>{
    val classes = ArrayList<String>()
    for (p in patterns){
        if(!classes.contains(p.clase)){
            classes.add(p.clase)
        }
    }
    return classes.toTypedArray()
}

class Reader{
    companion object{
        lateinit var data : Array<Pattern>
        private lateinit var file : File

        fun getFile() : JFileChooser{
            val fileChooser = JFileChooser()
            fileChooser.currentDirectory = File("./")
            fileChooser.showOpenDialog(fileChooser)

            return fileChooser
        }

        fun readFile(filter: Array<Boolean> = arrayOf()){
            if(!Companion::file.isInitialized){
                file = getFile().selectedFile
            }

            val fileReader = file.bufferedReader()

            val dataTmp = ArrayList<Pattern>()

            try {
                var line = fileReader.readLine()
                while (line != null) {
                    val tokensInit = line.split(",")

                    if(filter.isNotEmpty()
                            && tokensInit.size-1 != filter.size){
                        throw Exception("El tamaño del filtro no concuerda con el tamaño del vector de entrada. iFilter=${filter.size}, iVector=${tokensInit.size-1}")
                    }

                    val tokens = tokensInit.filterIndexed { i, s ->
                        when {
                            filter.isEmpty() -> true
                            i == tokensInit.size-1 -> true
                            else -> filter[i]
                        }
                    }

                    if (tokens.isNotEmpty()) {
                        dataTmp.add(Pattern(Array(tokens.size - 1) {
                            tokens[it].toDouble()
                        }, tokens.last()))
                    }

                    line = fileReader.readLine()
                }

                data = dataTmp.toTypedArray()
                fileReader.close()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fileReader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
