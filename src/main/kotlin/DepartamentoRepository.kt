package org.example

interface DepartamentoRepository {
    fun create(departamento: Departamento): Departamento
    fun read(id: Long): Departamento?
    fun update(departamento: Departamento): Departamento
    fun delete(id: Long): Boolean
    fun getAll(): List<Departamento>
}