package models

import utils.RGB
import java.awt.Color
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

class Image (image: File,
             width: Int,
             height: Int) : Pattern(width*height){
    val vectorColor : Array<RGB>

    init {
        try {
            val bufferedImage = ImageIO.read(image)

            vectorColor = Array(height*width){
                val numericColor = bufferedImage.getRGB(it%width, it/height)
                val color = Color(numericColor)
                vector[it] = numericColor.toDouble()
                RGB(color.red, color.green, color.blue)
            }

            clase = image.name.split("_").first()
        }catch (e : ArrayIndexOutOfBoundsException){
            throw Exception("El archivo ${image.absolutePath} no cumple con las limitaciones de tamaño")
        }
    }
}