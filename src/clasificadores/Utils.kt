package clasificadores

import java.io.IOException
import javax.swing.JFileChooser
import kotlin.math.pow
import kotlin.math.sqrt

/*
Creado por ramir el sábado 17 de agosto del 2019 a las 15:12 para PatternRecognition2020-1
*/
fun euclidianDistanceOf(pattern1: Pattern, pattern2: Pattern) : Double{
    if(pattern1.vector.size != pattern2.vector.size) throw Exception("El tamaño de los vectores es diferente")

    var sum = 0.0

    pattern1.vector.forEachIndexed { index, value ->
        sum += (pattern2.vector[index]-value).pow(2)
    }

    return sqrt(sum)
}

class Reader{
    companion object{
        val data = ArrayList<Pattern>()

        fun readFile(filter: Array<Boolean> = arrayOf()){
            val fileChooser = JFileChooser()
            fileChooser.showOpenDialog(fileChooser)

            val fileReader = fileChooser.selectedFile.bufferedReader()

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
                        data.add(Pattern(Array(tokens.size-1){
                            tokens[it].toDouble()
                        }, tokens.last()))
                    }

                    line = fileReader.readLine()
                }
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
