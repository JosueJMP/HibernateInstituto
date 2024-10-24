package org.example

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

object HibernateUtils {
    private var emf: EntityManagerFactory? = null

    // Método para obtener el EntityManagerFactory
    fun getEntityManagerFactory(): EntityManagerFactory {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("prueba1")
        }
        return emf!!
    }

    // Método para obtener un EntityManager a partir del EntityManagerFactory
    fun getEntityManager(): EntityManager {
        return getEntityManagerFactory().createEntityManager()
    }

    // Método para cerrar un EntityManager específico
    fun closeEntityManager(entityManager: EntityManager) {
        if (entityManager.isOpen) {
            entityManager.close()
        }
    }

    // Método para cerrar el EntityManagerFactory cuando ya no se necesite
    fun shutdown() {
        if (emf != null) {
            emf!!.close()
        }
    }
}
