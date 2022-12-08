package models

import java.util.*
import javax.persistence.*

/**
 * @author Sebastian Mendoza y Mario Resa
 */

@Embeddable
data class Raqueta(
    var uuid: UUID = UUID.randomUUID(),
    var marca: String,
    var modelo: String
)
