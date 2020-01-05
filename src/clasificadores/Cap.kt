package clasificadores

import utils.patternsMean

class Cap : LernMatrix() {
    fun ajustar(){
        val mean = patternsMean(this.getDataset().toList())

        for(r in getMatrix()){
            r.minus(mean)
        }
    }
}