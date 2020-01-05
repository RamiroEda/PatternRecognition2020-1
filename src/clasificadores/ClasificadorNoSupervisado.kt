package clasificadores

import models.Pattern

interface ClasificadorNoSupervisado {
    fun train(patterns: Array<Pattern>)
    fun classify(pattern: Pattern)
    fun classify(patterns: Array<Pattern>)
}