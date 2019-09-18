package models

import utils.RGB
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

class Image (val image: File,
             width: Int,
             height: Int) : Pattern(width*height){
    val vectorColor : Array<RGB>


    companion object{
        fun toBufferedImage(file: File): BufferedImage = ImageIO.read(file)
        fun toBufferedImage(file: Image): BufferedImage = ImageIO.read(file.image)
    }

    init {
        try {
            val bufferedImage = toBufferedImage(image)

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