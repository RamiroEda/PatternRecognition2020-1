package models

import clasificadores.kmeans.Centroid
import clasificadores.minima_distancia.images.ImageViewer
import utils.RGB
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

class Image (val image: File,
             val width: Int,
             val height: Int) : Pattern(width*height){
    val vectorColor : Array<RGB>

    constructor(image: File) : this(image, toBufferedImage(image).width, toBufferedImage(image).height)

    companion object{
        fun toBufferedImage(file: File): BufferedImage = ImageIO.read(file)
        fun toBufferedImage(file: Image): BufferedImage = ImageIO.read(file.image)
        fun show(image: Array<Pattern>, width: Int, height: Int){
            ImageViewer(image, width, height)
        }

        fun fromCentroids(centroids: ArrayList<Centroid>, image: Array<Pattern>){
            for (pixel in image){
                val c = centroids[centroids.indexOfFirst {
                    it.position.clase == pixel.claseResultante
                }]

                pixel.vector = c.position.vector
            }
        }
    }

    init {
        try {
            val bufferedImage = toBufferedImage(image)


            val v = ArrayList<RGB>()
            for (y in 0 until bufferedImage.height) {
                for (x in 0 until bufferedImage.width) {
                    val numericColor = bufferedImage.getRGB(x, y)
                    val color = Color(numericColor)
                    v.add(RGB(color.red, color.green, color.blue))
                }
            }

            vectorColor = v.toTypedArray()

            clase = image.name.split("_").first()
        } catch (e: ArrayIndexOutOfBoundsException) {
            throw Exception("El archivo ${image.absolutePath} no cumple con las limitaciones de tamaño. width=$width, height=$height")
        }
    }
}