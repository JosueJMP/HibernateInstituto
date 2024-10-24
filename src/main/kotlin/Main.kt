package org.example

fun main() {
    // Obtener EntityManager
    val entityManager = HibernateUtils.getEntityManager()

    // Crear el repositorio de Departamento
    val departamentoRepository = DepartamentoRepositoryImpl(entityManager)

    // Crear un nuevo departamento
    println("=== Creación de un nuevo departamento ===")
    val nuevoDepartamento = Departamento("Fisica", "Ciencias", "Departamento de Ciencias", null)
    val departamentoCreado = departamentoRepository.create(nuevoDepartamento)
    println("Departamento creado: $departamentoCreado")

    // Leer el departamento por ID
    println("\n=== Lectura del departamento creado ===")
    val departamentoLeido = departamentoRepository.read(id = departamentoCreado.id!!)
    if (departamentoLeido != null) {
        println("Departamento leído: $departamentoLeido")
    } else {
        println("Departamento no encontrado")
    }

    // Actualizar el departamento
    println("\n=== Actualización del departamento ===")
    if (departamentoLeido != null) {
        // Modificar los campos del departamento directamente
       departamentoLeido.nombre = "Ciencias Actualizado"
        departamentoLeido.descripcion = "Departamento de Ciencias Actualizado"

        // Utilizar merge() para sincronizar los cambios en la base de datos
        val actualizado = departamentoRepository.update(departamentoLeido)
        println("Departamento actualizado: $actualizado")
    }

    // Obtener todos los departamentos
    println("\n=== Obtener todos los departamentos ===")
    val todosLosDepartamentos = departamentoRepository.getAll()
    println("Lista de departamentos: $todosLosDepartamentos")

    // Eliminar el departamento
    println("\n=== Eliminación del departamento ===")
    val eliminado = departamentoRepository.delete(departamentoCreado.id!!)
    if (eliminado) {
        println("Departamento eliminado correctamente")
    } else {
        println("No se pudo eliminar el departamento")
    }

    // Obtener todos los departamentos después de eliminar
    println("\n=== Obtener todos los departamentos después de eliminar ===")
    val departamentosDespuesDeEliminar = departamentoRepository.getAll()
    println("Lista de departamentos después de eliminar: $departamentosDespuesDeEliminar")

    // Cerrar EntityManager y EntityManagerFactory
    HibernateUtils.closeEntityManager(entityManager)
    HibernateUtils.shutdown()
}
