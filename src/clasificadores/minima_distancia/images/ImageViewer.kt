package clasificadores.minima_distancia.images

import models.ImageRepresentativePattern
import models.Pattern
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.WindowConstants

class ImageViewer (private val representativePattern: Array<Pattern>,
                   private val imageWidth : Int,
                   private val imageHeight : Int) : JFrame() {
    companion object{
        const val OFFSET = 30
    }

    init {
        size = Dimension(imageWidth, imageHeight + OFFSET)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        isVisible = true
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        for(y in 0 until imageHeight){
            for(x in 0 until imageWidth){
                val index = (y*imageWidth)+x
                g?.color = Color(representativePattern[index].vector[0].toInt(), representativePattern[index].vector[1].toInt(), representativePattern[index].vector[2].toInt())

                g?.drawLine(x,y+ OFFSET,x,y+ OFFSET)
            }
        }
    }
}