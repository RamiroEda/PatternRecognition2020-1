package utils

import models.Pattern
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import java.awt.Dimension
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.WindowConstants
import kotlin.math.max

class Graficador2D (private val patterns : Array<Pattern>, private val graph : Boolean = false) : JFrame("Patrones por su resultado"), KeyListener {
    private var xIndex = 0
    private var yIndex = 1
    private val maxSize = patterns.first().vector.size

    init {
        newChart()
        addKeyListener(this)

        size = Dimension(800, 600)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }

    fun newChart(){
        val dataset = createDataset()
        val chart = initGraph(dataset)
        contentPane.removeAll()
        contentPane.add(ChartPanel(chart))
        contentPane.revalidate()
        contentPane.repaint()
    }

    private fun createDataset() : XYDataset{
        val dataset = XYSeriesCollection()

        val collections = HashMap<String, XYSeries>()

        getResultClasses(patterns).forEach {
            collections[it] = XYSeries(it)
        }

        for(p in patterns){
            collections[p.claseResultante]?.add(p.vector[xIndex], p.vector[yIndex])
        }

        for(c in collections){
            dataset.addSeries(c.value)
        }

        return dataset
    }

    private fun initGraph(dataset: XYDataset) : JFreeChart = ChartFactory.createScatterPlot(
        "",
        "Caracteristica $xIndex",
        "Caracteristica $yIndex",
        dataset,
        PlotOrientation.HORIZONTAL,
        true, false, false)

    override fun keyTyped(p0: KeyEvent) {
        when(p0.keyChar){
            'a' -> {
                if(--yIndex <= xIndex){
                    if(--xIndex < 0){
                        xIndex = maxSize-2
                    }
                    yIndex = maxSize-1
                }
            }
            'd' -> {
                if(++yIndex >= maxSize){
                    if(++xIndex >= maxSize-1){
                        xIndex = 0
                    }
                    yIndex = xIndex+1
                }
            }
        }

        newChart()
    }

    override fun keyPressed(p0: KeyEvent?) {}

    override fun keyReleased(p0: KeyEvent?) {}
}