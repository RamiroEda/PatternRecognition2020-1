package utils

import clasificadores.minima_distancia.images.ImageViewer
import models.Image
import models.ImageRepresentativePattern
import models.Pattern
import java.io.File
import java.io.FileFilter


fun viewRepresentativePattern(representativePatterns: List<ImageRepresentativePattern>, imageWidth: Int, imageHeight: Int){
    for(pattern in representativePatterns){
        ImageViewer(pattern, imageWidth, imageHeight).isVisible = true
    }
}

class ImageSet (directory: String,
                imageWidth: Int,
                imageHeight: Int) {

    val images = ArrayList<Image>()

    init {
        val folder = File(directory)

        if(folder.listFiles() != null){
            for (file in folder.listFiles(FileFilter {
                it.extension == "jpg" || it.extension == "png" || it.extension == "jpeg"
            })!!){
                images.add(Image(file, imageWidth, imageHeight))
            }
        }else{
            throw Exception("El directorio seleccionado no es una carpeta o esta vacio")
        }
    }

    fun toPatternArray(): Array<Pattern>{
        return images.toTypedArray()
    }
}