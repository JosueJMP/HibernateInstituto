package org.example

import jakarta.persistence.EntityManager

class DepartamentoRepositoryImpl(private val entityManager: EntityManager) : DepartamentoRepository {

    override fun create(departamento: Departamento): Departamento {
        entityManager.transaction.begin()
        entityManager.persist(departamento)
        entityManager.transaction.commit()
        return departamento
    }

    override fun read(id: Long): Departamento? {
        return entityManager.find(Departamento::class.java, id)
    }

    override fun update(departamento: Departamento): Departamento {
        entityManager.transaction.begin()
        val updatedDepartamento = entityManager.merge(departamento)
        entityManager.transaction.commit()
        return updatedDepartamento
    }

    override fun delete(id: Long): Boolean {
        val departamento = read(id)
        return if (departamento != null) {
            entityManager.transaction.begin()
            entityManager.remove(departamento)
            entityManager.transaction.commit()
            true
        } else {
            false
        }
    }

    override fun getAll(): List<Departamento> {
        val query = entityManager.createQuery("SELECT d FROM Departamento d", Departamento::class.java)
        return query.resultList
    }
}
