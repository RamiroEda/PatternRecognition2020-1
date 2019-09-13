package clasificadores.minima_distancia

import models.ImageRepresentativePattern
import models.RepresentativePattern
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.WindowConstants

class ImageViewerOriginal (private val representativePattern: RepresentativePattern,
                   private val imageWidth : Int,
                   private val imageHeight : Int) : JFrame(representativePattern.clase) {
    companion object{
        const val OFFSET = 30
    }

    init {
        size = Dimension(imageWidth, imageHeight + OFFSET)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        for ((i, pattern) in representativePattern.vector.withIndex()){
            g?.color = Color(pattern.toInt())
            val x = i%imageWidth
            val y = (i/imageHeight)+ OFFSET
            g?.drawLine(x,y,x,y)
        }
    }
}