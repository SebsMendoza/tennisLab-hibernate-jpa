package repository.tarea

import database.HibernateManager
import models.Tarea
import mu.KotlinLogging
import javax.persistence.TypedQuery

/**
 * @author Sebastian Mendoza y Mario Resa
 */

private val log = KotlinLogging.logger { }

class TareaRepositoryImpl : TareaRepository {
    override fun findAll(): List<Tarea> {
        log.debug { "findAll()" }
        var tareas = mutableListOf<Tarea>()
        HibernateManager.query {
            val query: TypedQuery<Tarea> =
                HibernateManager.manager.createNamedQuery("Tarea.findAll", Tarea::class.java)
            tareas = query.resultList
        }
        return tareas
    }

    override fun findById(id: Long): Tarea? {
        log.debug { "findById($id)" }
        var tarea: Tarea? = null
        HibernateManager.query {
            tarea = HibernateManager.manager.find(Tarea::class.java, id)
        }
        return tarea
    }

    override fun save(entity: Tarea): Tarea {
        log.debug { "save($entity)" }
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Tarea): Boolean {
        var result = false
        log.debug { "delete($entity)" }
        HibernateManager.transaction {
            val tarea = HibernateManager.manager.find(Tarea::class.java, entity.id)
            tarea?.let {
                HibernateManager.manager.remove(it)
                result = true
            }
        }
        return result
    }
}